package com.sejong.rental.controller;


import com.sejong.rental.dto.RentalReqDto;
import com.sejong.rental.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders = "*")
@Tag(name = "대여품 API", description = "대여품 단일 조회, 모두 조회")
public class ItemController {

    private final ItemService itemService;

    /** 대여품 전체 조회 */
    @PostMapping("/items")
    @Operation(summary = "대여품 전체 조회", description = "관리자, 일반 유저 모두 이걸로 조회")
    public ResponseEntity<?> getAllItem(@RequestParam("page") int page,
                                        @RequestParam("size") int size){
        return ResponseEntity.status(HttpStatus.OK)
                .body(itemService.getAllItems(page, size));
    }

    /** 단일 대여품 조회 */
    @Operation(summary = "단일 대여품 조회", description = "관리자, 일반 유저 모두 이걸로 조회")
    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getItem(@PathVariable("itemId") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(itemService.getItem(id));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
