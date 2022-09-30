package customvalidator.controller;

import customvalidator.model.User;
import customvalidator.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping("/user")
    public List<User> fetchAllUsers(){
       return userService.fetchAllUsers();
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid user id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping("/user/{userId}")
    public User fetchUserById(@PathVariable Long userId){
        return userService.fetchUserById(userId);
    }

    @Operation(summary = "Save a User")
    @PostMapping("/user")
    public User saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @Operation(summary = "Save or Update a User")
    @PutMapping("/user/{userId}")
    public User saveOrUpdateUser(@Valid @RequestBody User user, @PathVariable Long userId){
        return userService.saveOrUpdateUser(user, userId);
    }

    @Operation(summary = "Delete a User")
    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

}
