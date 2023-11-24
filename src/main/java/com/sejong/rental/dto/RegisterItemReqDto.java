package com.sejong.rental.dto;


import com.sejong.rental.entity.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterItemReqDto {
    private String itemName;
    private Integer cnt;
    private Integer total;
    private String image;
}
