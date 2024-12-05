package com.abdulmo123.connectwave.service;


import com.abdulmo123.connectwave.dto.ResetPasswordRequestDto;
import com.abdulmo123.connectwave.dto.UserDto;
import com.abdulmo123.connectwave.model.entity.AppUserDetails;
import com.abdulmo123.connectwave.model.entity.User;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<User> getUserFriendships(Long userId) {
        return userRepository.getUserFriendships(userId);
    }

    public List<UserDto> getPendingSentFriendshipRequests(Long userId) {
        return userRepository.getPendingSentFriendshipRequests(userId);
    }

    public List<UserDto> getPendingReceivedFriendshipRequests(Long userId) {
        return userRepository.getPendingReceivedFriendshipRequests(userId);
    }

    public User findUserByResetPwdToken(String token) {
        return userRepository.findUserByResetPwdToken(token);
    }

    public String resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByResetPwdToken(resetPasswordRequestDto.getToken()));

        if (user.isEmpty() || user.get().getResetPwdTokenExpDt().isBefore(LocalDateTime.now())) {
            return "Invalid or expired token";
        }

        user.get().setPassword(new PasswordGenerator().encodePassword(resetPasswordRequestDto.getNewPassword()));
        user.get().setResetPwdToken(null);
        user.get().setResetPwdTokenExpDt(null);
        userRepository.save(user.get());

        return "Password has been successfully reset.";
    }
}
