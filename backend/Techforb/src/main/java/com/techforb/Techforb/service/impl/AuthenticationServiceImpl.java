package com.techforb.Techforb.service;

import com.techforb.Techforb.dto.request.LoginRequest;
import com.techforb.Techforb.dto.request.SignUpRequest;
import com.techforb.Techforb.dto.response.JwtAuthenticationResponse;
import com.techforb.Techforb.exceptions.EmailAlreadyExistsException;
import com.techforb.Techforb.exceptions.NumberDocumentAlreadyExistException;
import com.techforb.Techforb.exceptions.ResourceNotFoundException;
import com.techforb.Techforb.models.Role;
import com.techforb.Techforb.models.User;
import com.techforb.Techforb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException();
        }

        if (userRepository.existsByNumberDocument(request.getNumberDocument())){
            throw new NumberDocumentAlreadyExistException();
        }

        var user = User
                .builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .numberDocument(request.getNumberDocument())
                .typeDocument(request.getTypeDocument())
                .role(Role.ROLE_USER)
                .build();

        user = userService.createUser(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse login(LoginRequest request) {
         User userValid = userRepository.findByNumberDocument(request.getNumberDocument())
                 .orElseThrow(()->
                         new ResourceNotFoundException(String.format("User with number document %s not found",
                         request.getNumberDocument())));
         if (!(String.valueOf(userValid.getTypeDocument()) == String.valueOf(request.getTypeDocument()))){
             throw new ResourceNotFoundException(String.format("User with type document %s  not found",
                     request.getTypeDocument()));
         }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userValid.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(userValid.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
