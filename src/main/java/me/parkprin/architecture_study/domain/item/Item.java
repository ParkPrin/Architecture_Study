package me.parkprin.architecture_study.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stackQuantity;

    @Builder
    public Item(String name, int price, int stackQuantity){
        this.name = name;
        this.price = price;
        this.stackQuantity = stackQuantity;
    }
}
