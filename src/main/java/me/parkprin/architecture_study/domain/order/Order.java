package me.parkprin.architecture_study.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.parkprin.architecture_study.domain.member.Member;
import me.parkprin.architecture_study.domain.orderitem.OrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member){

        if (this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setOrderStatus(String orderStatus){
        if (orderStatus.equals("ORDER")){
            this.status = OrderStatus.ORDER;
        } else {
            this.status = OrderStatus.CANCEL;
        }
    }

    public enum OrderStatus {
        ORDER, CANCEL
    }



    @Builder
    public Order(OrderStatus status){
        this.status = status;
    }
}
