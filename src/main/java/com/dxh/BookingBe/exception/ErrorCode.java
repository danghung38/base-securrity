package com.dxh.BookingBe.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "User existed", HttpStatus.BAD_REQUEST),
    PHONE_EXISTED(400, "Phone existed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(404, "Role not existed", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(400, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_NAME(400, "Name cannot be left blank", HttpStatus.BAD_REQUEST),
    INVALID_PHONENUMBER(400, "Enter correct phone number format", HttpStatus.BAD_REQUEST),
    INVALID_GENDER(400, "gender must be any of {MALE, FEMALE, OTHERS}", HttpStatus.BAD_REQUEST),
    INVALID_BLANK(400, "Please do not leave the required information blank", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(400, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    UPLOAD_FAIL(400, "Error uploading file to S3", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(400, "Invalid request", HttpStatus.BAD_REQUEST),
    INVALID_FORMATDOB(400, "Dob must be format yyyy/MM/dd", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
//        this.timestamp = new Date();
    }

    private int code;
    private String message;
//    private Date timestamp;
    private HttpStatusCode statusCode;
}
