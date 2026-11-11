package com.oabga.identity_service.controller;

import com.oabga.identity_service.dto.request.ApiResponse;
import com.oabga.identity_service.dto.request.UserCreationRequest;
import com.oabga.identity_service.dto.request.UserUpdateRequest;
import com.oabga.identity_service.entity.User;
import com.oabga.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserByUserId(userId);
    }

    @PutMapping("{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("{userId}")
    void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }
}
