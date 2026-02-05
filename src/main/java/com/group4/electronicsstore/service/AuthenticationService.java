package com.group4.electronicsstore.service;

import com.group4.electronicsstore.dto.request.LoginRequest;
import com.group4.electronicsstore.dto.request.RegisterRequest;
import com.group4.electronicsstore.dto.request.ChangePasswordRequest;
import com.group4.electronicsstore.dto.response.RegisterResponse;
import com.group4.electronicsstore.dto.response.UserResponse;

public interface AuthenticationService {

    UserResponse login(LoginRequest request);

    RegisterResponse register(RegisterRequest request);

    void changePassword(String username, ChangePasswordRequest request);

    UserResponse refreshToken(String refreshToken);

}
