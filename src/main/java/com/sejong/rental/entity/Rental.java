package com.sejong.rental.entity;

import com.sejong.rental.AuditingField;
import com.sejong.rental.dto.RentalReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Rental extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentNo;
    private String name;
    private String password;
    private Integer cnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;



    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    @Builder
    public Rental(RentalReqDto rentalReqDto) {
        this.studentNo = rentalReqDto.getStudentNo();
        this.name = rentalReqDto.getName();
        this.password = rentalReqDto.getPassword();
        this.cnt = rentalReqDto.getCnt();
    }

    public void updateRental(RentalReqDto rentalReqDto){
        this.name = rentalReqDto.getName();
        this.cnt = rentalReqDto.getCnt();
        this.password = rentalReqDto.getPassword();
        this.studentNo = rentalReqDto.getStudentNo();
    }


    public void setItem(Item item) {
        this.item = item;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}
