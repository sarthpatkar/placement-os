package com.sarth.placementos.service;


import com.sarth.placementos.entity.Track;
import com.sarth.placementos.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TrackService {


private final TrackRepository repository;


public Track create(Track track){

    return repository.save(track);

}


public List<Track> getAll(){

    return repository.findAll();

}

}