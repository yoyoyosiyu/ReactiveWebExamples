package com.huayutech.eventsourcing.axon.repository;

import com.huayutech.eventsourcing.axon.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
