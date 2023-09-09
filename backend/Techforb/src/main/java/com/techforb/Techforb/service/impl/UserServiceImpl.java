package com.techforb.Techforb.service.impl;

import com.techforb.Techforb.dto.request.UserRequestDTO;
import com.techforb.Techforb.dto.response.UserResponseDTO;
import com.techforb.Techforb.exceptions.ResourceNotFoundException;
import com.techforb.Techforb.mapper.UserMapper;
import com.techforb.Techforb.models.User;
import com.techforb.Techforb.repository.UserRepository;
import com.techforb.Techforb.service.CardService;
import com.techforb.Techforb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final CardService cardService;


    @Override
    public User createUser(User newUser){
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        repository.save(newUser);
        newUser.addCard(cardService.createCard(newUser));
        return newUser;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return repository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = repository.findAll();
        try {
            List<UserResponseDTO> response = users.stream()
                .map(userMapper::userToUserResponseDTO).collect(Collectors.toList());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUserByEmail(String email) {
        try {
            User user = repository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found " + email));
            UserResponseDTO responseDTO = userMapper.userToUserResponseDTO(user);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new UserResponseDTO(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<UserResponseDTO> updateUserById(Long id, UserRequestDTO request) {
        try {
            repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found " + id));
            User user = userMapper.userRequestDTOToUser(request);
            user.setId(id);
            return new ResponseEntity<>(userMapper.userToUserResponseDTO(repository.save(user)),HttpStatus.OK);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new UserResponseDTO(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public void uploadProfilePhoto(String email, MultipartFile file){
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found " + email));

        // Guardar el usuario con la URL de la imagen
        repository.save(user);
    };

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
