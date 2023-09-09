package com.techforb.Techforb.controller;

import com.techforb.Techforb.config.security.jwt.JwtAuthenticationFilter;
import com.techforb.Techforb.dto.request.LoginRequest;
import com.techforb.Techforb.dto.request.SignUpRequest;
import com.techforb.Techforb.dto.request.UserRequestDTO;
import com.techforb.Techforb.dto.response.JwtAuthenticationResponse;
import com.techforb.Techforb.dto.response.UserResponseDTO;
import com.techforb.Techforb.service.AuthenticationService;
import com.techforb.Techforb.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;


    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@Valid @RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        try{
            return userService.getAllUsers();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable @PositiveOrZero String email){
        return userService.getUserByEmail(email);
    }


    @PutMapping("user/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@Valid @PathVariable Long id,
                                                      @RequestBody UserRequestDTO requestDTO){
        return userService.updateUserById(id,requestDTO);
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
