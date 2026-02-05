package com.group4.electronicsstore.controller;

import com.group4.electronicsstore.dto.request.ChangePasswordRequest;
import com.group4.electronicsstore.dto.request.LoginRequest;
import com.group4.electronicsstore.dto.request.RefreshTokenRequest;
import com.group4.electronicsstore.dto.request.RegisterRequest;
import com.group4.electronicsstore.dto.response.ChangePasswordResponse;
import com.group4.electronicsstore.dto.response.ResponseObject;
import com.group4.electronicsstore.dto.response.UserResponse;
import com.group4.electronicsstore.exception.exceptions.BadRequestException;
import com.group4.electronicsstore.exception.exceptions.ForbiddenException;
import com.group4.electronicsstore.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    // ================= LOGIN =================
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseObject> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        UserResponse userResponse = authenticationService.login(loginRequest);

        return ResponseEntity.ok(
                new ResponseObject(
                        HttpStatus.OK.value(),
                        "Login successful",
                        userResponse
                )
        );
    }


    // ================= REGISTER =================
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseObject> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        authenticationService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(
                        HttpStatus.CREATED.value(),
                        "Đăng ký tài khoản thành công",
                        null
                ));
    }

    // ================= CHANGE PASSWORD =================
    @PutMapping("/change-password")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseObject> changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        authenticationService.changePassword(username, request);

        ChangePasswordResponse data = new ChangePasswordResponse(
                username,
                java.time.LocalDateTime.now()
        );

        return ResponseEntity.ok(
                new ResponseObject(
                        HttpStatus.OK.value(),
                        "Đổi mật khẩu thành công",
                        data
                )
        );
    }
    @PostMapping("/refresh-token")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseObject> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        UserResponse userResponse =
                authenticationService.refreshToken(request.getRefreshToken());

        return ResponseEntity.ok(
                new ResponseObject(
                        HttpStatus.OK.value(),
                        "Refresh token thành công",
                        userResponse
                )
        );
    }

}