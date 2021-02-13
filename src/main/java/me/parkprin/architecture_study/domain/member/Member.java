package me.parkprin.architecture_study.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.architecture_study.domain.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String city;
    private String street;
    private String zipcode;

    @OneToMany
    private List<Order> orders = new ArrayList<Order>();

    @Builder
    public Member(String name, String city, String street, String zipcode){
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
