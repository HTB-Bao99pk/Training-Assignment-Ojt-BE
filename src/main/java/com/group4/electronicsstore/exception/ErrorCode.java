package com.group4.electronicsstore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    //LỖI KO XÁC ĐỊNH ĐƯỢC
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(98, "Uncategorized error", HttpStatus.BAD_REQUEST),
    NOT_BLANK(99, "Field must not be blank", HttpStatus.BAD_REQUEST),
    // =================== LỖI ĐĂNG NHẬP (100-199) ================
    USER_NOT_FOUND(100, "User not found", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS(101, "Username already exists", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS(102, "Email already exists", HttpStatus.BAD_REQUEST),
    OLD_PASSWORD_WRONG(103, "Old password is incorrect", HttpStatus.BAD_REQUEST),
    CONFIRM_PASSWORD_NOT_MATCH(104, "Confirm password does not match", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(105, "Account has been locked", HttpStatus.LOCKED),
    USERNAME_OR_PASSWORD_INVALID(106, "Username or password is invalid", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN(107, "Refresh token is invalid or expired", HttpStatus.UNAUTHORIZED),
    USER_NAME_AND_PASSWORD_NOT_BLANK(108, "Username and password must not be blank", HttpStatus.BAD_REQUEST),
    USER_NAME_LENGTH_INVALID(109, "Username length must be between 6 and 28 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_LENGTH_INVALID(110, "Password length must be at least 6", HttpStatus.BAD_REQUEST),
    CONFIRM_PASSWORD_NOT_BLANK(111, "Confirm password must not be blank", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_BLANK(112, "Email must not be blank", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(113, "Email is invalid", HttpStatus.BAD_REQUEST),
    FULL_NAME_IS_REQUIRED(114, "Email is required", HttpStatus.BAD_REQUEST),
    FULL_NAME_LENGTH_INVALID(115, "Full name must be between 2–50 characters", HttpStatus.BAD_REQUEST),

    // =====================LỖI PRODUCTS(200 -299)==================
    PRODUCT_NOT_FOUND(200, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_NAME_EXISTED(201, "Product name already exists", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_REQUIRED(202, "Product name is required", HttpStatus.BAD_REQUEST),
    PRICE_MUST_BE_POSITIVE(203, "Price must be a positive value", HttpStatus.BAD_REQUEST),
    STOCK_QUANTITY_REQUIRED(204, "Stock quantity is required", HttpStatus.BAD_REQUEST),
    STOCK_QUANTITY_CANNOT_BE_NEGATIVE(205, "Stock quantity cannot be negative", HttpStatus.BAD_REQUEST),
    PRICE_REQUIRED(206, "Price is required", HttpStatus.BAD_REQUEST),


    // ======================LỖI CATEGORY(300-399)====================
    CATEGORY_NOT_FOUND(300, "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_ID_IS_REQUIRED(301, "Category is required", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_EXISTED(302, "Category name already exists", HttpStatus.BAD_REQUEST);


    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}

