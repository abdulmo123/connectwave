package com.abdulmo123.connectwave.repository;

import com.abdulmo123.connectwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
