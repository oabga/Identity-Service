package com.oabga.identity_service.service;

import com.oabga.identity_service.dto.request.UserCreationRequest;
import com.oabga.identity_service.dto.request.UserUpdateRequest;
import com.oabga.identity_service.entity.User;
import com.oabga.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserId(String userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstname());
        user.setPassword(request.getPassword());
        user.setLastName(request.getLastname());
        user.setDob(request.getDob());

        userRepository.save(user);

        return user;
    }

    public String deleteUser(String userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);

        return "User has been deleted";
    }
}
