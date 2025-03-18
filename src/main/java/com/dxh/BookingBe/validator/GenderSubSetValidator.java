package com.dxh.BookingBe.validator;

import com.dxh.BookingBe.enums.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GenderSubSetValidator implements ConstraintValidator<GenderSubset, String> {
//    private Gender[] genders;
//
//    @Override
//    public void initialize(GenderSubset constraint) {
//        this.genders = constraint.anyOf();
//    }
//
//    @Override
//    public boolean isValid(Gender value, ConstraintValidatorContext context) {
//        return value == null || Arrays.asList(genders).contains(value);
//    }
    private String[] genderValues;

    @Override
    public void initialize(GenderSubset constraint) {
        // Lưu danh sách giá trị hợp lệ dưới dạng String
        genderValues = Arrays.stream(constraint.anyOf())
                .map(Enum::name) // Chuyển Enum thành String
                .toArray(String[]::new);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // Cho phép null
        return Arrays.asList(genderValues).contains(value.toUpperCase()); // Kiểm tra giá trị
    }
}
