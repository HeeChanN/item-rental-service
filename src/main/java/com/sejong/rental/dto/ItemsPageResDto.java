package com.sejong.rental.dto;


import com.sejong.rental.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsPageResDto {
    List<Item> items;
    PageInfo pageInfo;
}
