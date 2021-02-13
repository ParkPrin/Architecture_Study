package me.parkprin.architecture_study.service.orderitem;

import me.parkprin.architecture_study.domain.item.Item;
import me.parkprin.architecture_study.domain.member.Member;
import me.parkprin.architecture_study.domain.order.Order;
import me.parkprin.architecture_study.domain.orderitem.OrderItem;
import me.parkprin.architecture_study.service.item.ItemService;
import me.parkprin.architecture_study.service.member.MemberService;
import me.parkprin.architecture_study.service.order.OrderService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    MemberService memberService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;


    @After
    public void cleanup(){
        orderItemService.orderItemRepository.deleteAll();
        itemService.itemRepository.deleteAll();
        orderService.orderRepository.deleteAll();
        memberService.memberRepository.deleteAll();
    }

    @Test
    public void 등록한_멤버가_상품을_주문한다(){
        Member member = memberService.memberRepository.save(
                Member.builder().name("박종훈").city("서울시 금천구").street("금하로 816").zipcode("1234").build());
        Item macbook = Item.builder().name("맥북").price(3000000).stackQuantity(10).build();
        Item gram = Item.builder().name("LG gram").price(1800000).stackQuantity(20).build();
        Item monitor = Item.builder().name("맥북").price(300000).stackQuantity(15).build();
        itemService.itemRepository.save(macbook);
        itemService.itemRepository.save(gram);
        itemService.itemRepository.save(monitor);
        Order order = orderService.orderRepository.save(Order.builder().status(Order.OrderStatus.ORDER).build());
        order.setMember(member);
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.add(OrderItem.builder().item(macbook).order(order).count(1).orderPrice(macbook.getPrice()*1).build());
        orderItems.add(OrderItem.builder().item(gram).order(order).count(1).orderPrice(gram.getPrice()*1).build());
        orderItemService.orderItemRepository.saveAll(orderItems);

        int wholePrice = 0 ;
        for (Order eachOrder : member.getOrders()){
            for (OrderItem eachOrderItem: eachOrder.getOrderItems()){
                System.out.println("주문자: " + member.getName() + " 주문번호: "
                        + eachOrder.getId() + " 상품명: " + eachOrderItem.getItem().getName() +
                        " 상품 주문 가격" + eachOrderItem.getOrderPrice() +  "상품 주문 개수: " + eachOrderItem.getCount());

                wholePrice = wholePrice + eachOrderItem.getOrderPrice();
            }
            System.out.println(" 주문번호: " + eachOrder.getId() + " 총결재금액: " + wholePrice );
        }

        assertThat(orderItems.size()).isEqualTo(2);
    }
}
