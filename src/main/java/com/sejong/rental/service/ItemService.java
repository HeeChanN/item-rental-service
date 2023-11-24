package com.sejong.rental.service;


import com.sejong.rental.dto.ItemResDto;
import com.sejong.rental.dto.ItemsPageResDto;
import com.sejong.rental.dto.PageInfo;
import com.sejong.rental.dto.RentalReqDto;
import com.sejong.rental.entity.Item;
import com.sejong.rental.entity.RentalStatus;
import com.sejong.rental.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemsPageResDto getAllItems(int page, int size){
        Page<Item> items = itemRepository.findAll(PageRequest.of(page,size));

        PageInfo pageInfo= PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(items.getTotalPages())
                .totalNumber(items.getTotalElements())
                .build();

        return ItemsPageResDto
                .builder()
                .items(items.getContent().stream().map(o->new ItemResDto(o)).collect(Collectors.toList()))
                .pageInfo(pageInfo)
                .build();
    }

    public ItemResDto getItem(Long id) throws Exception {
        return itemRepository.findById(id).map(o->new ItemResDto(o))
                .orElseThrow(()->new Exception("해당 아이템이 존재하지 않습니다."));
    }



}
