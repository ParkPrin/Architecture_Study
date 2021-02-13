package me.parkprin.architecture_study.domain.orderitem;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.parkprin.architecture_study.domain.item.Item;
import me.parkprin.architecture_study.domain.order.Order;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count){
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
