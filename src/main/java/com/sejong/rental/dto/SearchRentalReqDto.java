package com.sejong.rental.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRentalReqDto {
    private String studentNo;
    private String password;
}
