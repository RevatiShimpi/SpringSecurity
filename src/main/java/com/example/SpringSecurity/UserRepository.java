package com.example.SpringSecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<Users> findByUserName(String userName);

}
