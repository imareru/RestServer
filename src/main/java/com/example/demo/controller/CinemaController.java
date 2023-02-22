package com.example.demo.controller;

import com.example.demo.entity.Cinema;
import com.example.demo.repository.CinemaRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.resource.CinemaResource;
import com.example.demo.resource.RoomResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/cinema")
//@EnableAutoConfiguration

public class CinemaController {
    private final CinemaRepository cinemaRepository;
    private final RoomRepository roomRepository;

    public CinemaController(CinemaRepository cinemaRepository, RoomRepository roomRepository) {
        this.cinemaRepository = cinemaRepository;
        this.roomRepository = roomRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    CinemaResource[] getAll(@RequestParam(required = false) String address,
                            @RequestParam(required = false) Object expand) {
        Cinema[] entities = address == null ?
                cinemaRepository.select():
                cinemaRepository.selectByAddress(address);
        return Arrays.stream(entities)
                .map(entity -> {
                    CinemaResource resource = new CinemaResource(entity);
                    if (expand != null)
                        resource.setRoom(
                                Arrays.stream(roomRepository.selectBySourceId(entity.getId()))
                                        .map(e -> new RoomResource(e))
                                        .toArray(RoomResource[]::new)
                        );
                    return resource;
                })
                .toArray(CinemaResource[]::new);
    }
   // cinemaRepository.select()

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    CinemaResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Cinema entity = cinemaRepository.select(id);
        if (entity == null) return null;
        CinemaResource resource = new CinemaResource(entity);
        if (expand != null)
            resource.setRoom(
                    Arrays.stream(roomRepository.selectBySourceId(entity.getId()))
                            .map(e -> new RoomResource(e))
                            .toArray(RoomResource[]::new)
            );
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    CinemaResource post(@RequestBody CinemaResource resource) {
        Cinema entity = cinemaRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new CinemaResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    CinemaResource put(@PathVariable Integer id,
                          @RequestBody CinemaResource resource) {
        Cinema entity = cinemaRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new CinemaResource(entity);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    CinemaResource delete(@PathVariable Integer id) {
        Cinema entity = cinemaRepository.delete(id);
        if (entity == null) return null;
        return new CinemaResource(entity);
    }
}
