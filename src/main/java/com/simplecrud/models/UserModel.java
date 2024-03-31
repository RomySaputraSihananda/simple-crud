package com.simplecrud.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel{
    private String id;
    private String username;
    private String password;
}
