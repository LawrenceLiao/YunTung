package com.yuntung.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yuntung.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Artist getArtistByName(String name) {
        return mapper.load(Artist.class, name);
    }
}
