package com.sejong.rental.service;



import com.sejong.rental.dto.AdminRentalResDto;
import com.sejong.rental.dto.RegisterItemReqDto;
import com.sejong.rental.entity.Item;
import com.sejong.rental.entity.Rental;
import com.sejong.rental.entity.RentalStatus;
import com.sejong.rental.repository.ItemRepository;
import com.sejong.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ItemRepository itemRepository;
    private final RentalRepository rentalRepository;

    @Transactional
    public String registerItem(RegisterItemReqDto registerItemReqDto){
        Item item = Item.builder()
                .name(registerItemReqDto.getItemName())
                .cnt(registerItemReqDto.getCnt())
                .url(registerItemReqDto.getImage())
                .build();

        itemRepository.save(item);
        return "등록완료";
    }

    @Transactional
    public String updateItem(Long id, RegisterItemReqDto itemReqDto) throws Exception{
        Item item =itemRepository.findById(id).orElseThrow(()-> new Exception("해당 아이템이 존재하지 않습니다."));

        item.setAll(itemReqDto);
        itemRepository.save(item);
        return "수정 완료";
    }

    @Transactional
    public String deleteItem(Long id)throws Exception{
        itemRepository.deleteById(id);
        return "삭제 완료";
    }

    public List<AdminRentalResDto> getAllRental(){
        return rentalRepository.findAll().stream()
                .map(o->new AdminRentalResDto(o))
                .collect(Collectors.toList());
    }

    public String checkRental(Long id) throws Exception{
        Rental rental = rentalRepository.findById(id).orElseThrow(()->new Exception("해당 대여기록이 존재하지 않습니다."));
        rental.setStatus(RentalStatus.RENTAL);

        return "대여 완료";
    }

    public String changeStatus(Long id) throws Exception{
        Rental rental = rentalRepository.findById(id).orElseThrow(()->new Exception("해당 대여기록이 존재하지 않습니다."));
        rental.getItem().returnCnt(rental.getCnt());
        rental.setStatus(RentalStatus.RETURN);

        rentalRepository.save(rental);

        return "반납 완료";
    }

    public String deleteRental(Long id) throws Exception{
        Rental rental = rentalRepository.findById(id).orElseThrow(()->new Exception("해당 대여기록이 존재하지 않습니다."));
        rentalRepository.delete(rental);

        return "삭제완료";
    }
}
