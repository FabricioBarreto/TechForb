package com.techforb.Techforb.service;

import com.techforb.Techforb.dto.request.UserRequestDTO;
import com.techforb.Techforb.dto.response.UserResponseDTO;
import com.techforb.Techforb.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    ResponseEntity<List<UserResponseDTO>> getAllUsers();
    ResponseEntity<UserResponseDTO> getUserById(Long id);

    ResponseEntity<UserResponseDTO> updateUserById(Long id, UserRequestDTO request);

    void deleteUser(Long id);

    User createUser(User newUser);

    UserDetailsService userDetailsService();

}
