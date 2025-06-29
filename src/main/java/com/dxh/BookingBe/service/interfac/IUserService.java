package com.dxh.BookingBe.service.interfac;

import com.dxh.BookingBe.dto.request.UserCreationRequest;
import com.dxh.BookingBe.dto.response.PageResponse;
import com.dxh.BookingBe.dto.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    UserResponse createUser(UserCreationRequest request);

    PageResponse<List<UserResponse>> getAllUsersSortBy(int pageNo, int pageSize, String sortBy);

    PageResponse<List<UserResponse>> getAllUsersSortByMultipleColumns(int pageNo, int pageSize, String... sorts);

    PageResponse<List<UserResponse>> advanceSearchWithSpecifications(Pageable pageable, String[] user, String[] role);
}
