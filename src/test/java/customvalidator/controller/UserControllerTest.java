package customvalidator.controller;

import customvalidator.model.Address;
import customvalidator.model.User;
import customvalidator.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;


    @Test
    public void fetchAllUsersTest(){

        User user1 = getUser1();

        List<User> users = new ArrayList<User>();
        users.add(user1);

        when(userService.fetchAllUsers()).thenReturn(users);
        List<User> fetchedUsers = userController.fetchAllUsers();
        assertEquals(users.size(), fetchedUsers.size());
        assertEquals(user1.getName(), fetchedUsers.get(0).getName());
    }

    @Test
    public void saveUserTest(){

        User user1 = getUser1();

        when(userService.saveUser(Mockito.any())).thenReturn(user1);
        User fetchedUser = userController.saveUser(user1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }


    @Test
    public void fetchUserByIdTest(){
        User user1 = getUser1();
        when(userService.fetchUserById(Mockito.any())).thenReturn(user1);
        User fetchedUser = userController.fetchUserById((long)1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }

    @Test
    public void saveOrUpdateUserTest(){
        User user1 = getUser1();
        Optional<User> opt = Optional.of(user1);
        when(userService.saveOrUpdateUser(Mockito.any(), Mockito.anyLong())).thenReturn(user1);
        User fetchedUser = userController.saveOrUpdateUser(user1, (long)1);
        assertEquals(user1.getName(), fetchedUser.getName());
    }

    @Test
    public void deleteUserTest(){
        User user1 = getUser1();
        Optional<User> opt = Optional.of(user1);
        when(userService.deleteUser(Mockito.anyLong())).thenReturn("Deleted user with userId: " + (long)1);
        String response = userController.deleteUser((long)1);
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
