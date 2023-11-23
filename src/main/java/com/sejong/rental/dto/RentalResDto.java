package com.sejong.rental.dto;


import com.sejong.rental.entity.Rental;
import com.sejong.rental.entity.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RentalResDto {
    private Long rentalId;
    private String itemName;
    private String url;
    private Integer cnt;
    private RentalStatus status;

    public RentalResDto(Rental rental) {
        this.rentalId = rental.getId();
        this.itemName = rental.getItem().getName();
        this.url = rental.getItem().getUrl();
        this.cnt = rental.getItem().getCnt();
        this.status = rental.getStatus();
    }
}
