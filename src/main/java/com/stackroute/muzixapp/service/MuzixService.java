package com.stackroute.muzixapp.service;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;

import java.util.List;

public interface MuzixService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

   public void deleteById(int id) throws TrackNotFoundException;

   public boolean updateById(Track track, int id) throws TrackNotFoundException;

    public  List<Track> getAllTracks();

    public Track getTrackById(int trackId) throws TrackNotFoundException;

}
