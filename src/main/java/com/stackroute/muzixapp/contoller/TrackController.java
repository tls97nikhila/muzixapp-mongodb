package com.stackroute.muzixapp.contoller;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import com.stackroute.muzixapp.service.MuzixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "muzixapp/v1")
public class TrackController {
    MuzixService muzixService;

    public TrackController(MuzixService muzixService){
        this.muzixService=muzixService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            muzixService.saveTrack(track);
            responseEntity= new ResponseEntity("Successfully created", HttpStatus.CREATED);

        }catch (TrackAlreadyExistsException ex){
            responseEntity= new ResponseEntity<String>(ex.getMessage() , HttpStatus.CONFLICT );
            ex.printStackTrace();

        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            muzixService.deleteById(id);
        } catch (TrackNotFoundException e) {
            responseEntity= new ResponseEntity<String>(e.getMessage() , HttpStatus.CONFLICT );
        }
        return new ResponseEntity<List<Track>>(muzixService.getAllTracks(), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public String updateTrack(@RequestBody Track track, @PathVariable int id){
        try {
            muzixService.updateById(track,id);
        } catch (TrackNotFoundException e) {
            e.getMessage();
        }
        return "Updated";


    }
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(muzixService.getAllTracks(),HttpStatus.OK);
    }
    @GetMapping("track/{trackId}")
    public ResponseEntity<?> getTrackById(@PathVariable int trackId){
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<Track>(muzixService.getTrackById(trackId), HttpStatus.OK);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
