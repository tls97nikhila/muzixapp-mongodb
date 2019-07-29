package com.stackroute.muzixapp;

import com.stackroute.muzixapp.domain.Track;
import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.repository.MuzixRepository;
import com.stackroute.muzixapp.service.MuzixServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MuzixServiceTest {
    Track track;

    @Mock
    MuzixRepository repository;

    @InjectMocks
    MuzixServiceImpl service;
    List<Track> list = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        track = new Track(1, "Name", "Comments");
        list = new ArrayList<>();
        list.add(track);
    }
    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {

        when(repository.save((Track) any())).thenReturn(track);
        Track savedUser = service.saveTrack(track);
        Assert.assertEquals(track,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(repository,times(1)).save(track);

    }


    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(repository.save((Track) any())).thenReturn(null);
        Track savedTrack = service.saveTrack(track);
        System.out.println("saved Track" + savedTrack);



    }

    @Test
    public void getAllTracks(){

        repository.save(track);
        //stubbing the mock to return specific data
        when(repository.findAll()).thenReturn(list);
        List<Track> tracklist = service.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }
}
