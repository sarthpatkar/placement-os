package com.sarth.placementos.controller;


import com.sarth.placementos.entity.Track;
import com.sarth.placementos.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor

public class TrackController {


private final TrackService service;


@PostMapping
public Track create(
        @RequestBody Track track
){

    return service.create(track);

}



@GetMapping
public List<Track> all(){

    return service.getAll();

}


}