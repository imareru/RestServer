package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.resource.ClientResource;
import com.example.demo.resource.OrderResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/client")
//@EnableAutoConfiguration
public class ClientController {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public ClientController(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    ClientResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(clientRepository.select())
                .map(entity -> {
                    ClientResource resource = new ClientResource(entity);
                    if (expand != null)
                        resource.setOrder(
                                Arrays.stream(orderRepository.selectBySourceId(entity.getId()))
                                        .map(e -> new OrderResource(e))
                                        .toArray(OrderResource[]::new)
                        );
                    return resource;
                })
                .toArray(ClientResource[]::new);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ClientResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Client entity = clientRepository.select(id);
        if (entity == null) return null;
        ClientResource resource = new ClientResource(entity);
        if (expand != null)
            resource.setOrder(
                    Arrays.stream(orderRepository.selectBySourceId(entity.getId()))
                            .map(e -> new OrderResource(e))
                            .toArray(OrderResource[]::new)
            );
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientResource post(@RequestBody ClientResource resource) {
        Client entity = clientRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new ClientResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ClientResource put(@PathVariable Integer id,
                          @RequestBody ClientResource resource) {
        Client entity = clientRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new ClientResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ClientResource delete(@PathVariable Integer id) {
        Client entity = clientRepository.delete(id);
        if (entity == null) return null;
        return new ClientResource(entity);
    }
}
