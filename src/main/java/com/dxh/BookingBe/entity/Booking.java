package com.dxh.BookingBe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends AbstractEntity<Long> {

    LocalDate checkInDate;

    @Future(message = "check out date must be in the future")
    LocalDate checkOutDate;

    @Column(name = "number_of_adults")
    int numberOfAdults;

    @Column(name = "number_of_children")
    int numberOfChildren;

    @Column(name = "total_number_of_guest")
    int totalNumberOfGuest;

    @Column(name = "booking_confirm_code")
    String bookingConfirmCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest() {
        this.totalNumberOfGuest = this.numberOfAdults + this.numberOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        this.numberOfAdults = numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.numberOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + getId() +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numOfAdults=" + numberOfAdults +
                ", numOfChildren=" + numberOfChildren +
                ", totalNumOfGuest=" + totalNumberOfGuest +
                ", bookingConfirmationCode='" + bookingConfirmCode + '\'' +
                '}';
    }
}
