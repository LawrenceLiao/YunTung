package com.yuntung.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.yuntung.entities.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MusicRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public List<Music> getListByInfo(String title, String artist, String year) {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(title));
        eav.put(":val2", new AttributeValue().withS(artist));
        eav.put(":val3", new AttributeValue().withS(year));

        Map<String, String> expression = new HashMap<>();

        expression.put("#yr", "year");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("contains(title, :val1) and contains(artist, :val2) and contains(#yr, :val3)")
                .withExpressionAttributeNames(expression)
                .withExpressionAttributeValues(eav);

        List<Music> listOfMusics = mapper.scan(Music.class, scanExpression);
        return listOfMusics;
    }
}
