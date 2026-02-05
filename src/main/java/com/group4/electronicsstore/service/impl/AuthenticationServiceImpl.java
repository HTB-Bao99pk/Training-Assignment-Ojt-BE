package com.group4.electronicsstore.service.impl;

import com.group4.electronicsstore.dto.request.ChangePasswordRequest;
import com.group4.electronicsstore.dto.request.LoginRequest;
import com.group4.electronicsstore.dto.request.RegisterRequest;
import com.group4.electronicsstore.dto.response.RegisterResponse;
import com.group4.electronicsstore.dto.response.UserResponse;
import com.group4.electronicsstore.entity.Role;
import com.group4.electronicsstore.entity.User;
import com.group4.electronicsstore.exception.AppException;
import com.group4.electronicsstore.exception.ErrorCode;
import com.group4.electronicsstore.repository.UserRepository;
import com.group4.electronicsstore.security.JwtTokenProvider;
import com.group4.electronicsstore.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // ================= LOGIN =================
    @Override
    public UserResponse login(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AppException(ErrorCode.USERNAME_OR_PASSWORD_INVALID);
        } catch (LockedException e) {
            throw new AppException(ErrorCode.ACCOUNT_LOCKED);
        }

        User user = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() ->
                        new AppException(ErrorCode.USER_NOT_FOUND)
                );

        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(user.getRole())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // ================= REGISTER =================
    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setRole(Role.USER);

        userRepository.save(user);

        return RegisterResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole().name())
                .message("Đăng ký tài khoản thành công")
                .build();
    }

    // ================= CHANGE PASSWORD =================
    @Override
    public void changePassword(String username, ChangePasswordRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new AppException(ErrorCode.USER_NOT_FOUND)
                );

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.OLD_PASSWORD_WRONG);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new AppException(ErrorCode.CONFIRM_PASSWORD_NOT_MATCH);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    // ================= REFRESH TOKEN =================
    @Override
    public UserResponse refreshToken(String refreshToken) {

        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new AppException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        String username =
                jwtTokenProvider.getUsernameFromRefreshToken(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new AppException(ErrorCode.USER_NOT_FOUND)
                );

        String newAccessToken =
                jwtTokenProvider.generateAccessToken(user);

        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(user.getRole())
                .accessToken(newAccessToken)
                .build();
    }
}
