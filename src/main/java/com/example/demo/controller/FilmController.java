package com.example.demo.controller;

import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.resource.FilmResource;
import com.example.demo.resource.OrderResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
@CrossOrigin
@RestController
@RequestMapping(value = "/film")
//@EnableAutoConfiguration
public class FilmController {
    private final FilmRepository filmRepository;
    private final OrderRepository orderRepository;

    public FilmController(FilmRepository filmRepository, OrderRepository orderRepository) {
        this.filmRepository = filmRepository;
        this.orderRepository = orderRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    FilmResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(filmRepository.select())
                .map(entity -> {
                    FilmResource resource = new FilmResource(entity);
                    if (expand != null)
                        resource.setOrder(
                                Arrays.stream(orderRepository.selectBySourceId(entity.getId()))
                                        .map(e -> new OrderResource(e))
                                        .toArray(OrderResource[]::new)
                        );
                    return resource;
                })
                .toArray(FilmResource[]::new);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    FilmResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Film entity = filmRepository.select(id);
        if (entity == null) return null;
        FilmResource resource = new FilmResource(entity);
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
    FilmResource post(@RequestBody FilmResource resource) {
        Film entity = filmRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new FilmResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    FilmResource put(@PathVariable Integer id,
                          @RequestBody FilmResource resource) {
        Film entity = filmRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new FilmResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    FilmResource delete(@PathVariable Integer id) {
        Film entity = filmRepository.delete(id);
        if (entity == null) return null;
        return new FilmResource(entity);
    }
}
