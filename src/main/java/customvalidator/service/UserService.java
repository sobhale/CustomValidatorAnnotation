package customvalidator.service;

import customvalidator.model.User;
import customvalidator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User fetchUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("User not found with User Id: " + userId);
                }
        );
    }

    public User saveOrUpdateUser(User user, Long userId) {

        User foundUser = userRepository.findById(userId)
                .orElseThrow( () -> {
                    throw new ResourceNotFoundException("User not found with User Id: " + userId);
                });
                foundUser.setAddress(user.getAddress());
                foundUser.setEmail(user.getEmail());
                foundUser.setGender(user.getGender());
                foundUser.setId(userId);
                foundUser.setName(user.getName());

        return userRepository.save(foundUser);
    }

    public String deleteUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("User not found with User Id: " + userId);
                });

       userRepository.delete(user);
       return "Deleted user with userId: " + userId;
    }
}
