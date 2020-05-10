package com.huayutech.eventsourcing.axon.web;

import com.huayutech.eventsourcing.axon.domain.entity.Item;
import com.huayutech.eventsourcing.axon.service.ItemService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/commodities")
public class CommodityController {
    @Autowired
    ItemService itemService;

    public Flux<Item> findById(Long id) {
        return Flux.just(itemService.findById(id)).publishOn(Schedulers.parallel());
    }

    @GetMapping(value = "/{id}")
    public Flux<Item> getItemById(@PathVariable Long id) {
        System.out.println("start of method");
        Flux<Item> items = findById(id);
        items.subscribe(new Subscriber<Item>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Item item) {
                System.out.println("Retrieved: " + item.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

        System.out.println("end of method");

        return items;
    }

}
