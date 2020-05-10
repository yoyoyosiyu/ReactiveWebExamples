package com.huayutech.eventsourcing.axon.service;

import com.huayutech.eventsourcing.axon.domain.entity.Item;

public interface ItemService {

    Item findById(Long id);

}
