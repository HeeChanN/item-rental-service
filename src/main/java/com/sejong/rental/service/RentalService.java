package com.sejong.rental.service;

import com.sejong.rental.dto.ItemResDto;
import com.sejong.rental.dto.RentalReqDto;
import com.sejong.rental.dto.RentalResDto;
import com.sejong.rental.dto.SearchRentalReqDto;
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
public class RentalService {

    private final ItemRepository itemRepository;
    private final RentalRepository rentalRepository;
    @Transactional
    public String rentalItem(Long id, RentalReqDto rentalReqDto) throws Exception {
        Item item = itemRepository.findById(id)
                .orElseThrow(()->new Exception("해당 아이템이 존재하지 않습니다."));
        item.setCnt(rentalReqDto.getCnt());

        Rental rental = Rental.builder().rentalReqDto(rentalReqDto).build();
        rental.setStatus(RentalStatus.WAIT);
        rental.setItem(item);

        rentalRepository.save(rental);
        return "대여가 신청되었습니다.";
    }

    public List<RentalResDto> getRentals(SearchRentalReqDto searchRentalReqDto){
        return rentalRepository.findAllByStudentNoAndPassword(searchRentalReqDto.getStudentNo(),searchRentalReqDto.getPassword())
                .stream().map(o->new RentalResDto(o)).collect(Collectors.toList());

    }

    @Transactional
    public RentalResDto updateRental(Long id, RentalReqDto rentalReqDto)throws Exception{
        Rental rental = rentalRepository.findById(id).orElseThrow(()-> new Exception("해당 대여기록이 존재하지 않습니다."));

        rental.updateRental(rentalReqDto);
        rental.getItem().returnCnt(rental.getCnt());
        rental.getItem().setCnt(rentalReqDto.getCnt());
        //rentalRepository.save(rental);
        RentalResDto rentalResDto = new RentalResDto(rental);
        return rentalResDto;
    }

    public String deleteRental(Long id) throws Exception{
        Rental rental = rentalRepository.findById(id).orElseThrow(()-> new Exception("해당 대여기록이 존재하지 않습니다."));

        rental.getItem().returnCnt(rental.getCnt());
        rentalRepository.delete(rental);

        return "삭제 완료";
    }

}
