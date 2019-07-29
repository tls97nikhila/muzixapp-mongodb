package com.stackroute.muzixapp.repository;

import com.stackroute.muzixapp.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuzixRepository extends MongoRepository<Track,Integer> {

}
