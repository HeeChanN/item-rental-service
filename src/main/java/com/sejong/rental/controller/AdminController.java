package com.sejong.rental.controller;


import com.sejong.rental.dto.RegisterItemReqDto;
import com.sejong.rental.service.AdminService;
import com.sejong.rental.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "물품 등록", description = "대여품을 관리자가 등록한다.")
    @PostMapping("/item")
    public ResponseEntity<?> registerItem(@RequestBody RegisterItemReqDto registerItemReqDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.registerItem(registerItemReqDto));
    }


    @Operation(summary = "등록된 물품 수정", description = "물품 정보 수정")
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
    @Operation(summary = "등록된 물품 삭제", description = "관리자, 일반 유저 모두 이걸로 조회")
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
    @Operation(summary = "관리자 대여 기록 전체 조회", description = "관리자 조회 기능")
    @GetMapping("/rentals")
    public ResponseEntity<?> getAllRentals(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(adminService.getAllRental());
    }

    @Operation(summary = "대여 수락 버튼", description = "사용자가 대여 신청을하면 대기 상태인데 수락해주는 용")
    @PostMapping("/rental/{rentalId}")
    public ResponseEntity<?> checkRental(@PathVariable("rentalId") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.checkRental(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "반납 버튼", description = "관리자가 수동으로 반납을 확인하고 반납 완료 누르기")
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

    @Operation(summary = "이전 대여 기록 삭제", description = "이미 지난 기록 삭제삭제")
    @DeleteMapping("/rental/past/{rentalId}")
    public ResponseEntity<?> deleteRental(@PathVariable("rentalId") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(adminService.deleteRental(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
