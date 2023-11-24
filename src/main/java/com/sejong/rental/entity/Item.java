package com.sejong.rental.entity;


import com.sejong.rental.dto.RegisterItemReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer total;
    private Integer cnt;
    private String url;



    @Builder
    public Item(String name, Integer cnt, String url,Integer total) {
        this.name = name;
        this.cnt = cnt;
        this.url = url;
        this.total = total;
    }

    @OneToMany(mappedBy ="item",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Rental> rentalList = new ArrayList<Rental>();

    public void setAll(RegisterItemReqDto registerItemReqDto){
        this.name = registerItemReqDto.getItemName();
        this.total = registerItemReqDto.getCnt();
        this.url = registerItemReqDto.getImage();
    }

    public void setCnt(Integer cnt) {
        this.cnt = this.cnt - cnt;
    }
    public void returnCnt(Integer cnt){
        this.cnt = this.cnt +cnt;
    }


}
