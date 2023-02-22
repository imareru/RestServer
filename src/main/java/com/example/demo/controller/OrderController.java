package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.resource.ClientResource;
import com.example.demo.resource.FilmResource;
import com.example.demo.resource.OrderResource;
import com.example.demo.resource.RoomResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@CrossOrigin
@RestController
@RequestMapping(value = "/order")

public class OrderController {
    private final OrderRepository orderRepository;
    private final FilmRepository filmRepository;
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;

    public OrderController(OrderRepository orderRepository, FilmRepository filmRepository, RoomRepository roomRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.filmRepository = filmRepository;
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    OrderResource[] getAll(@RequestParam(required = false) Integer sourceId,
                             @RequestParam(required = false) Object expand) {
        Order[] entities = sourceId == null ?
                orderRepository.select() :
                orderRepository.selectBySourceId(sourceId);
        return Arrays.stream(entities)
                .map(entity -> {
                    OrderResource resource = new OrderResource(entity);
                    if (expand != null) {
                        resource.setFilm(new FilmResource(filmRepository.select(entity.getFilmId())));
                        resource.setClient(new ClientResource(clientRepository.select(entity.getClientId())));
                        resource.setRoom(new RoomResource(roomRepository.select(entity.getRoomId())));
                    }
                    return resource;
                })
                .toArray(OrderResource[]::new);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    OrderResource get(@PathVariable Integer id, @RequestParam(required = false) Object expand) {
        Order entity = orderRepository.select(id);
        if (entity == null) return null;
        OrderResource resource1 = new OrderResource(entity);
        if (expand != null){
            resource1.setFilm(new FilmResource(filmRepository.select(entity.getFilmId())));
            resource1.setClient(new ClientResource(clientRepository.select(entity.getClientId())));
            resource1.setRoom(new RoomResource(roomRepository.select(entity.getRoomId())));
        }
        return resource1;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    OrderResource post(@RequestBody OrderResource resource) {
        Order entity = orderRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new OrderResource(entity);
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    OrderResource put(@PathVariable Integer id,
                        @RequestBody OrderResource resource) {
        Order entity = orderRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new OrderResource(entity);
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    OrderResource delete(@PathVariable Integer id) {
        Order entity = orderRepository.delete(id);
        if (entity == null) return null;
        OrderResource resource = new OrderResource(entity);
        return resource;
    }
}
