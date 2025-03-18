package com.dxh.BookingBe.entity;

import com.dxh.BookingBe.enums.Gender;
import com.dxh.BookingBe.validator.PhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity<Long> {

    @Column(unique = true, nullable = false)
    String username;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String name;

    @Column(unique = true, nullable = false)
    String phoneNumber;

    String password;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate dob;

    @ManyToMany
    Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Booking> bookings = new ArrayList<>();

}