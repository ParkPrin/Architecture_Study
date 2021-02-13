package me.parkprin.architecture_study.service.item;

import me.parkprin.architecture_study.domain.item.Item;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemService itemService;


    @After
    public void cleanup(){
        itemService.itemRepository.deleteAll();
    }

    @Test
    public void 아이템_등록(){
        Item macbook = Item.builder().name("맥북").price(3000000).stackQuantity(10).build();
        itemService.itemRepository.save(macbook);
        List<Item> itemList = itemService.itemRepository.findAll();
        System.out.println(itemList);
        assertThat(itemList.size()).isEqualTo(1);
    }
}
