package com.techforb.Techforb.mapper;

import com.techforb.Techforb.dto.request.UserRequestDTO;
import com.techforb.Techforb.dto.response.UserResponseDTO;
import com.techforb.Techforb.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public UserRequestDTO userToUserRequestDTO(User user){
        return mapper.map(user, UserRequestDTO.class);
    }

    public User userRequestDTOToUser(UserRequestDTO requestDTO){
        return mapper.map(requestDTO, User.class);
    }

    public UserResponseDTO userToUserResponseDTO(User user){
        return mapper.map(user, UserResponseDTO.class);
    }

    public User userResponseDTOToUser(UserResponseDTO userResponseDTO){
        return mapper.map(userResponseDTO, User.class);
    }

}
