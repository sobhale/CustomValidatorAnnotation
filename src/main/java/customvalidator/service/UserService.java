package customvalidator.service;

import customvalidator.model.User;
import customvalidator.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private static final String USER_NOT_FOUND_MSG = "User not found with user Id: ";

    public List<User> fetchAllUsers(){
        LOGGER.info("Fetching all users from DB");
        return userRepository.findAll();
    }

    public User saveUser(User user){
        LOGGER.info("Saving a user to DB with user name: " + user.getName());
        LOGGER.debug("User being saved is : " + user);
        return userRepository.save(user);
    }

    public User fetchUserById(Long userId){
        LOGGER.info("Fetching a user with Id: " + userId);
        return userRepository.findById(userId).orElseThrow(
                () -> {

                    LOGGER.error(USER_NOT_FOUND_MSG + userId);
                    throw new ResourceNotFoundException(USER_NOT_FOUND_MSG + userId);
                }
        );
    }

    public User saveOrUpdateUser(User user, Long userId) {

        LOGGER.info("Updating user with Id: " + userId);
        User foundUser = userRepository.findById(userId)
                .orElseThrow( () -> {
                    LOGGER.error(USER_NOT_FOUND_MSG + userId);
                    throw new ResourceNotFoundException(USER_NOT_FOUND_MSG + userId);
                });
                foundUser.setAddress(user.getAddress());
                foundUser.setEmail(user.getEmail());
                foundUser.setGender(user.getGender());
                foundUser.setId(userId);
                foundUser.setName(user.getName());

        return userRepository.save(foundUser);
    }

    public String deleteUser(Long userId) {

        LOGGER.info("Deleting a user with Id: " + userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> {
                    LOGGER.error(USER_NOT_FOUND_MSG + userId);
                    throw new ResourceNotFoundException(USER_NOT_FOUND_MSG + userId);
                });

       userRepository.delete(user);
       return "Deleted user with userId: " + userId;
    }
}
