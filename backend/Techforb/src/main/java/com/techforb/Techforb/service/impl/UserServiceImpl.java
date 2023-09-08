package com.Techforb.Techforb.service.impl;

import com.Techforb.Techforb.config.amazon3.service.IAWSClientService;
import com.Techforb.Techforb.dto.request.UserRequestDTO;
import com.Techforb.Techforb.dto.response.UserResponseDTO;
import com.Techforb.Techforb.exceptions.ResourceNotFoundException;
import com.Techforb.Techforb.mapper.UserMapper;
import com.Techforb.Techforb.models.User;
import com.Techforb.Techforb.repository.CardRepository;
import com.Techforb.Techforb.repository.UserRepository;
import com.Techforb.Techforb.service.CardService;
import com.Techforb.Techforb.service.UserService;
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

    private final IAWSClientService iawsClientService;

    private final CardService cardService;

    private final CardRepository cardRepository;

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
            responseDTO.setImageUrl(iawsClientService.getImage(user.getImageUrl()));
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

        // Elimina la foto actual del usuario para ahorrar almacenamiento
        iawsClientService.deleteFileFromS3Bucket(user.getImageUrl());

        // Subir la imagen a Amazon S3
        iawsClientService.uploadFile(file);

        String fileName = iawsClientService.generateFileName(file);
        user.setImageUrl(fileName);

        // Guardar el usuario con la URL de la imagen
        repository.save(user);
    };

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
