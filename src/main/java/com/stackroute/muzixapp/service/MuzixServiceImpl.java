package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuzixServiceImpl implements MuzixService  {


    MuzixRepository muzixRepository;


    @Autowired
    public MuzixServiceImpl(MuzixRepository muzixRepository){
        this.muzixRepository=muzixRepository;
    }


    @Override
    public Track saveTrack(Track track)  throws TrackAlreadyExistsException {
        if(muzixRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("User already exists!");
        }
           Track savedTrack = muzixRepository.save(track);
        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("User already exists!");
        }
        return savedTrack;
    }

    @Override
    public void deleteById(int id) throws TrackNotFoundException {
        Optional<Track> userId = muzixRepository.findById(id);
       if(!userId.isPresent()){
            throw new TrackNotFoundException("Track not found!");
        }
        muzixRepository.deleteById(id);
    }
    public Track getTrackById(int trackId) throws TrackNotFoundException{
        if(!muzixRepository.existsById(trackId)){
            throw new TrackNotFoundException("Track Not Found");
        }
        Track track1 = muzixRepository.findById(trackId).get();
        if(track1==null){
            throw new TrackNotFoundException("Track Not Found");
        }
        return track1;
    }
    @Override
    public boolean updateById(Track track, int id) {
        Optional<Track> trackOptional = muzixRepository.findById(id);

        if(!trackOptional.isPresent())
        return false;

        track.setId(id);
        muzixRepository.save(track);
        return  true;
    }

    @Override
    public List<Track> getAllTracks() {
        return muzixRepository.findAll();
    }





}
