package com.sejong.rental.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalReqDto {
    private String name;
    private String password;
    private String studentNo;
    private Integer cnt;
}
