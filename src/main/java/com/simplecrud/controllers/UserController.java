package com.simplecrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplecrud.DTOs.UserDTO;
import com.simplecrud.models.UserModel;
import com.simplecrud.payloads.responses.BodyResponse;
import com.simplecrud.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping()
    @Operation(summary = "get all users")
    public  ResponseEntity<BodyResponse<List<UserModel>>> getAllUser(){
        return new ResponseEntity<BodyResponse<List<UserModel>>>(
            new BodyResponse<List<UserModel>>(
                HttpStatus.OK.getReasonPhrase(), 
                HttpStatus.OK.value(),
                "all data users",
                this.userService.getAllUsers()
            ),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user by id")
    public ResponseEntity<BodyResponse<UserModel>> getUserById(@RequestParam String id){
        return new ResponseEntity<BodyResponse<UserModel>>(
            new BodyResponse<UserModel>(
                HttpStatus.OK.getReasonPhrase(), 
                HttpStatus.OK.value(),
                String.format("data user with id %s", id),
                this.userService.getUserById(id)
            ),
            HttpStatus.OK
        );
    }

    @PostMapping()
    @Operation(summary = "add user")
    public ResponseEntity<BodyResponse<UserModel>> addUser(@RequestBody UserDTO user){
        return new ResponseEntity<BodyResponse<UserModel>>(
            new BodyResponse<UserModel>(
                HttpStatus.CREATED.getReasonPhrase(), 
                HttpStatus.CREATED.value(),
                "success add user",
                this.userService.addUser(new UserModel(UUID.randomUUID().toString(), user.getUsername(), user.getPassword()))
            ),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user")
    public ResponseEntity<BodyResponse<UserModel>> updateUser(@RequestBody UserDTO user, @RequestParam String id){
        return new ResponseEntity<BodyResponse<UserModel>>(
            new BodyResponse<UserModel>(
                HttpStatus.OK.getReasonPhrase(), 
                HttpStatus.OK.value(),
                String.format("success update user with id %s", id),
                this.userService.updateUser(id, new UserModel(id, user.getUsername(), user.getPassword()))
            ),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user")
    public ResponseEntity<BodyResponse<UserModel>> deleteUser(@RequestParam String id){
        this.userService.deleteUser(id);
        return new ResponseEntity<BodyResponse<UserModel>>(
            new BodyResponse<UserModel>(
                HttpStatus.OK.getReasonPhrase(), 
                HttpStatus.OK.value(),
                String.format("success delete user with id %s", id),
                null
            ),
            HttpStatus.OK
        );
    }
}
