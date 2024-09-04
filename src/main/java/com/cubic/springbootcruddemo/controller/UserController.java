package com.cubic.springbootcruddemo.controller;

import com.cubic.springbootcruddemo.model.Message;
import com.cubic.springbootcruddemo.model.User;
import com.cubic.springbootcruddemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @Operation(summary = "Create a New User", description = "Add a new User")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "204", description= "User Created successfully",
                    content ={
                            @Content(mediaType ="application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User Not Found!!",
                    content = @Content)
    })

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User saveUser = userService.saveUserInfo(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Update User Information", description = "Update the details of an existing User")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description= "User Updated Successfully",
                    content ={
                            @Content(mediaType ="application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Invalid Input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User Not Found!!",
                    content = @Content)
    })

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody @Schema(implementation = User.class) User user){
        User updatedUser = userService.updateUserInfo(id,user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @Operation(summary = "Delete a User by ID", description = "Delete a user by their ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "204", description= "Deleted a user successfully",
                    content ={
                            @Content(mediaType ="application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User Not Found!!",
                    content = @Content)
    })

    @DeleteMapping("{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable int id){
        String msg = userService.deleteUserInfo(id);
        return new ResponseEntity<>(new Message(msg), HttpStatus.OK);
    }

    @Operation(summary = "Get Users By ID", description = "Retrieve a user by their ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description= "Found the user",
                    content ={
                            @Content(mediaType ="application/json",
                                    schema = @Schema(implementation = User.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User Not Found!!",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get All Users", description = "Retrieve a list of all users")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description= "Found the users",
            content ={
                    @Content(mediaType ="application/json",
                    schema = @Schema(implementation = User.class))
            }),
            @ApiResponse(responseCode = "404", description = "User Not Found!!",
            content = @Content)
    })

    @GetMapping
    public ResponseEntity<List<User>> findAllUser(){
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
