package com.Techforb.Techforb.controller;

import com.Techforb.Techforb.config.amazon3.service.IAWSClientService;
import com.Techforb.Techforb.config.security.jwt.JwtAuthenticationFilter;
import com.Techforb.Techforb.dto.request.LoginRequest;
import com.Techforb.Techforb.dto.request.SignUpRequest;
import com.Techforb.Techforb.dto.request.UserRequestDTO;
import com.Techforb.Techforb.dto.response.JwtAuthenticationResponse;
import com.Techforb.Techforb.dto.response.UserResponseDTO;
import com.Techforb.Techforb.service.AuthenticationService;
import com.Techforb.Techforb.service.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IAWSClientService iawsClientService;

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

    @PostMapping("/upload/{email}")
    public ResponseEntity<String> uploadProfilePhoto(@PathVariable String email,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            iawsClientService.uploadFile(file);
            userService.uploadProfilePhoto(email,file);
            return ResponseEntity.ok("Imagen subida con éxito");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al subir la imagen");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
