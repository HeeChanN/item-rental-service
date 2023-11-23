package com.sejong.rental.dto;


import com.sejong.rental.entity.Rental;
import com.sejong.rental.entity.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRentalResDto {
    private Long rentalId;
    private Long itemId;
    private String studentNo;
    private String name;
    private LocalDateTime date;
    private String itemName;
    private Integer cnt;
    private RentalStatus status;

    public AdminRentalResDto(Rental rental) {
        this.rentalId = rental.getId();
        this.itemId = rental.getItem().getId();
        this.studentNo = rental.getStudentNo();
        this.name = rental.getName();
        this.date = rental.getCreatedAt();
        this.itemName = rental.getItem().getName();
        this.cnt = rental.getCnt();
        this.status = rental.getStatus();
    }
}
