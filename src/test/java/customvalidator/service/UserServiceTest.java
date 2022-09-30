package customvalidator.service;

import customvalidator.model.Address;
import customvalidator.model.User;
import customvalidator.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void fetchAllUsersTest(){

        User user1 = getUser1();

        List<User> users = new ArrayList<User>();
        users.add(user1);

        when(userRepository.findAll()).thenReturn(users);
        List<User> fetchedUsers = userService.fetchAllUsers();
        assertEquals(users.size(), fetchedUsers.size());
        assertEquals(user1.getName(), fetchedUsers.get(0).getName());
    }

    @Test
    public void saveUserTest(){

        User user1 = getUser1();

        when(userRepository.save(Mockito.any())).thenReturn(user1);
        User fetchedUser = userService.saveUser(user1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }


    @Test
    public void fetchUserByIdTest(){
        User user1 = getUser1();
        Optional<User> opt = Optional.of(user1);
        when(userRepository.findById(Mockito.any())).thenReturn(opt);
        User fetchedUser = userService.fetchUserById((long)1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }

    @Test
    public void saveOrUpdateUserTest(){
        User user1 = getUser1();
        Optional<User> opt = Optional.of(user1);
        when(userRepository.findById(Mockito.any())).thenReturn(opt);
        when(userRepository.save(Mockito.any())).thenReturn(user1);
        User fetchedUser = userService.saveOrUpdateUser(user1, (long)1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }

    @Test
    public void deleteUserTest(){
        User user1 = getUser1();
        Optional<User> opt = Optional.of(user1);
        when(userRepository.findById(Mockito.any())).thenReturn(opt);
        doNothing().when(userRepository).delete(user1);
        String response = userService.deleteUser((long)1);
        assertEquals("Deleted user with userId: " + (long)1, response);
    }

    private User getUser1() {
        User user1 = new User();
        user1.setName("abc");
        user1.setGender('M');
        user1.setEmail("abc@gmail.com");
        user1.setAddress(new Address());
        return user1;
    }


}
