package com.Techforb.Techforb.service;

import com.Techforb.Techforb.dto.request.LoginRequest;
import com.Techforb.Techforb.dto.request.SignUpRequest;
import com.Techforb.Techforb.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse login(LoginRequest request);

}
