package com.jpashop.jpashop.service;

import com.jpashop.jpashop.domain.item.Item;
import com.jpashop.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * em.merge(item)를 사용할 경우, 모든 필드를 교체하기 때문에 null 값에 대한 위험이 존재한다.
     * 그래서 아래의 코드와 같이 변경 감지 기능을 이용해서 변경된 데이터만 교체를 하는 것이 좋다.
     *
     * 파라미터가 많은 경우 DTO를 이용해서 간결하게 사용할 수 있다.
     */
    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
