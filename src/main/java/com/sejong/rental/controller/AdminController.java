package com.sejong.rental.controller;


import com.sejong.rental.dto.RegisterItemReqDto;
import com.sejong.rental.service.AdminService;
import com.sejong.rental.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "관리자 API", description = "물품 CRUD, 대여기록 조회,변경")
public class AdminController {

    private final AdminService adminService;


    /** 대여품 등록 */
    @PostMapping("/item")
    public ResponseEntity<?> registerItem(@RequestBody RegisterItemReqDto registerItemReqDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.registerItem(registerItemReqDto));
    }


    @PatchMapping("/item/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable("itemId") Long id, @RequestBody RegisterItemReqDto registerItemReqDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.updateItem(id, registerItemReqDto));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.deleteItem(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/rentals")
    public ResponseEntity<?> getAllRentals(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getAllRental());
    }

    @PostMapping("/rental/{rentalId}")
    public ResponseEntity<?> checkRental(@PathVariable("rentalId") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.getAllRental());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/rental")
    public ResponseEntity<?> changeStatus(@RequestParam("rentalId") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.changeStatus(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
