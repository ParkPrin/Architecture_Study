package me.parkprin.architecture_study.service.item;

import me.parkprin.architecture_study.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    public ItemRepository itemRepository;

}
