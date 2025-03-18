package com.dxh.BookingBe.mapper;

import com.dxh.BookingBe.dto.request.UserCreationRequest;
import com.dxh.BookingBe.dto.response.UserResponse;
import com.dxh.BookingBe.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

}
