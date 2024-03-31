package com.simplecrud.services;

import org.springframework.stereotype.Service;

import com.simplecrud.exceptions.UserIsExistsException;
import com.simplecrud.exceptions.UserNotFoundException;
import com.simplecrud.models.UserModel;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<UserModel> users = new ArrayList<UserModel>(List.of(new UserModel(UUID.randomUUID().toString(), "Romy", "123")));

    public List<UserModel> getAllUsers(){
        return this.users;
    }

    public UserModel getUserById(String id){
        UserModel userExists = this.userIsExists(id);

        if(userExists == null) throw new UserNotFoundException(String.format("user by id %s not found", id));
        
        return userExists;
    }

    public UserModel addUser(UserModel user){
        if(this.usernameIsExists(user.getUsername())) throw new UserIsExistsException(String.format("user with username %s is exists", user.getUsername()));
        this.users.add(user);
        return user;
    }

    public UserModel updateUser(String id, UserModel newUser){
        if(this.userIsExists(id) == null) throw new UserNotFoundException(String.format("user with id %s not found", id));

        this.users = this.users.stream().map(user -> {
            if(user.getId().equals(id)) return newUser;
            return user;
        }).collect(Collectors.toList());

        return newUser;
    }

    public void deleteUser(String id){
        if(this.userIsExists(id) == null) throw new UserNotFoundException(String.format("user with id %s not found", id));
        this.users.removeIf(user -> user.getId().equals(id));
    }

    private UserModel userIsExists(String id){
        return this.users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    private boolean usernameIsExists(String username){
        return this.users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
