package com.example.judesys.services;

import com.example.judesys.contracts.RoleRequest;
import com.example.judesys.contracts.RegisterUserRequest;
import com.example.judesys.contracts.UserRoleRequest;
import com.example.judesys.exceptions.ResourceNotFoundException;
import com.example.judesys.interfaces.IRoleRepository;
import com.example.judesys.interfaces.IUserRepository;
import com.example.judesys.interfaces.IUserService;
import com.example.judesys.models.Role;
import com.example.judesys.models.User;
//import org.springframework.security.core.userdetails.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(RegisterUserRequest registerUserRequest) {
        if(userRepository.findByUsername(registerUserRequest.getUserName()).isPresent())
            throw new ResourceNotFoundException("User", "username", registerUserRequest.getUserName());
        registerUserRequest.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        var user = registerUserRequest.getUser();
        user.getRoles().add(new Role("ROLE_USER"));

        return userRepository.save(user);
    }

    @Override
    public Role saveRole(RoleRequest roleRequest) {
        return roleRepository.save(roleRequest.getRole());
    }

    @Override
    public void addRoleToUser(UserRoleRequest userRole) {
        var user = userRepository.findByUsername(userRole.getUsername());
        var role = roleRepository.findByName(userRole.getRole());
        if(user.isPresent() && role.isPresent())
            user.get().getRoles().add(role.get());
    }

    @Override
    public User getUser(String username) {
        var user = userRepository.findByUsername(username);
        if(!user.isPresent())
            throw new ResourceNotFoundException("User", "username", username);
        return user.get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent())
            throw new ResourceNotFoundException("User", "username", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                authorities);
    }
}
