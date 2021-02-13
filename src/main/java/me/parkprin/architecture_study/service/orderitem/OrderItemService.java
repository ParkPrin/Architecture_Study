package me.parkprin.architecture_study.service.orderitem;

import me.parkprin.architecture_study.domain.orderitem.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    public OrderItemRepository orderItemRepository;
}
