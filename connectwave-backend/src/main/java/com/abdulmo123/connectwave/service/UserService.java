package com.abdulmo123.connectwave.service;


import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.entity.AppUserDetails;
import com.abdulmo123.connectwave.entity.User;
import com.abdulmo123.connectwave.exception.UserNotFoundException;
import com.abdulmo123.connectwave.repository.UserRepository;
import com.abdulmo123.connectwave.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found!";

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        user.setPassword(passwordGenerator.encodePassword(user.getPassword()));
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new AppUserDetails(user);
    }

    public boolean isValidUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found!"));
    }

    public UserDto findUserByPostId(Long postId) {
        return userRepository.findUserByPostId(postId);
    }
}
