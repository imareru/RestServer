package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.repository.CinemaRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.resource.CinemaResource;
import com.example.demo.resource.RoomResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/room")
//@EnableAutoConfiguration
public class RoomController {
    private final RoomRepository roomRepository;
    private final CinemaRepository cinemaRepository;

    public RoomController(RoomRepository roomRepository, CinemaRepository cinemaRepository) {
        this.roomRepository = roomRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    RoomResource[] getAll(@RequestParam(required = false) Integer sourceId,
                          @RequestParam(required = false) Object expand) {
        Room[] entities = sourceId == null ?
                roomRepository.select() :
                roomRepository.selectBySourceId(sourceId);
        return Arrays.stream(entities)
                .map(entity -> {
                    RoomResource resource = new RoomResource(entity);
                    if (expand != null)
                        resource.setCinema(new CinemaResource(cinemaRepository.select(entity.getCinemaId())));
                    return resource;
                })
                .toArray(RoomResource[]::new);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    RoomResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Room entity = roomRepository.select(id);
        if (entity == null) return null;
        RoomResource resource = new RoomResource(entity);
        if (expand != null)
            resource.setCinema(
                    new CinemaResource(cinemaRepository.select(entity.getCinemaId()))
            );
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.POST)
    RoomResource post(@RequestBody RoomResource resource) {
        Room entity = roomRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new RoomResource(entity);
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    RoomResource put(@PathVariable Integer id,
                        @RequestBody RoomResource resource) {
        Room entity = roomRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new RoomResource(entity);
        return resource;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    RoomResource delete(@PathVariable Integer id) {
        Room entity = roomRepository.delete(id);
        if (entity == null) return null;
        RoomResource resource = new RoomResource(entity);
        return resource;
    }
}
