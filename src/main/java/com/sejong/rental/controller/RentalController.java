package com.sejong.rental.controller;


import com.sejong.rental.dto.RentalReqDto;
import com.sejong.rental.dto.SearchRentalReqDto;
import com.sejong.rental.service.RentalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders = "*")
@Tag(name = "대여 API", description = "대여 신청, 수정, 삭제")
public class RentalController {

    private final RentalService rentalService;

    /** 대여 신청 */
    @PostMapping ("/item/{itemId}")
    public ResponseEntity<?> rentalItem(@PathVariable("itemId")Long id, @RequestBody RentalReqDto rentalReqDto){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(rentalService.rentalItem(id, rentalReqDto));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    /** 대여 내역 조회 */
    @GetMapping("/rentals")
    public ResponseEntity<?> getRentalByInfo(@RequestBody SearchRentalReqDto searchRentalReqDto){
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.getRentals(searchRentalReqDto));
    }

    /** 대여내역 수정 */

    @PatchMapping("/rental/{rentalId}")
    public ResponseEntity<?> updateRental(@PathVariable("rentalId") Long id, @RequestBody RentalReqDto rentalReqDto){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(rentalService.updateRental(id,rentalReqDto));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /** 대여내역 삭제 */
    @DeleteMapping("/rental/{rentalId}")
    public ResponseEntity<?> deleteRental(@PathVariable("rentalId") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(rentalService.deleteRental(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
