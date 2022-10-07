package com.example.judesys.interfaces;

import com.example.judesys.contracts.RoleRequest;
import com.example.judesys.contracts.RegisterUserRequest;
import com.example.judesys.contracts.UserRoleRequest;
import com.example.judesys.models.Role;
import com.example.judesys.models.User;

import java.util.List;

public interface IUserService {
    User saveUser(RegisterUserRequest registerUserRequest);
    Role saveRole(RoleRequest role);
    void addRoleToUser(UserRoleRequest userRoleRequest);
    User getUser(String username);
    List<User> getUsers();
}
