package com.example.judesys.controllers;

import com.example.judesys.contracts.RoleRequest;
import com.example.judesys.contracts.RegisterUserRequest;
import com.example.judesys.contracts.UserRoleRequest;
import com.example.judesys.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class AuthController {
    private final IUserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return new ResponseEntity<>(userService.saveUser(registerUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/roles/")
    public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest) {
        return new ResponseEntity<>(userService.saveRole(roleRequest), HttpStatus.CREATED);
    }

    @PutMapping("/roles/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody UserRoleRequest userRoleRequest) {
        userService.addRoleToUser(userRoleRequest);
    }
}
