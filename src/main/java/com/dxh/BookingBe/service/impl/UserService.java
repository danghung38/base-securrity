package com.dxh.BookingBe.service.impl;

import com.dxh.BookingBe.dto.request.UserCreationRequest;
import com.dxh.BookingBe.dto.response.UserResponse;
import com.dxh.BookingBe.entity.User;
import com.dxh.BookingBe.enums.Role;
import com.dxh.BookingBe.exception.AppException;
import com.dxh.BookingBe.exception.ErrorCode;
import com.dxh.BookingBe.mapper.UserMapper;
import com.dxh.BookingBe.repo.RoleRepository;
import com.dxh.BookingBe.repo.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new AppException(ErrorCode.PHONE_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRoles(Set.of(
                roleRepository.findByName(Role.USER.name())
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED))
        ));

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
