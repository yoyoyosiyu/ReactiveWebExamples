package com.huayutech.eventsourcing.axon.service.impl;

import com.huayutech.eventsourcing.axon.domain.entity.Item;
import com.huayutech.eventsourcing.axon.repository.ItemRepository;
import com.huayutech.eventsourcing.axon.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
