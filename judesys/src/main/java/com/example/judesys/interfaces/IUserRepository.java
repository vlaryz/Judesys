package com.example.judesys.interfaces;

import com.example.judesys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

//    List<User> findByEmail(String email);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

//    Boolean existsByEmail(String email);
}
