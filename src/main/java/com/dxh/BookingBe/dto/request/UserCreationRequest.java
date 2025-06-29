package com.dxh.BookingBe.dto.request;

import com.dxh.BookingBe.enums.Gender;
import com.dxh.BookingBe.validator.DobConstraint;
import com.dxh.BookingBe.validator.EnumValue;
import com.dxh.BookingBe.validator.GenderSubset;
import com.dxh.BookingBe.validator.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6,message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    @NotBlank(message = "INVALID_NAME")
    String name;

    @PhoneNumber(message = "INVALID_PHONENUMBER")
    @NotBlank(message = "INVALID_BLANK")
    String phoneNumber;


//    @EnumValue(name = "gender", enumClass = Gender.class)
    @GenderSubset(anyOf = {Gender.MALE, Gender.FEMALE, Gender.OTHER},message = "INVALID_GENDER")
    String gender;



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}