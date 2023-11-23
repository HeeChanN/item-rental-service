package com.sejong.rental.dto;


import com.sejong.rental.entity.Item;
import com.sejong.rental.entity.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResDto {
    private String itemName;
    private Integer cnt;
    private String image;

    public ItemResDto(Item item) {
        this.itemName=item.getName();
        this.cnt =item.getCnt();
        this.image = item.getUrl();
    }

}
