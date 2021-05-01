package com.yuntung.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.yuntung.entities.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SubscriptionRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Subscription add(Subscription subscription) {
        mapper.save(subscription);
        return subscription;
    }

    public void delete(Subscription subscription) {
        mapper.delete(subscription);
    }

    public List<Subscription> findListByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1",new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("subscriber_email = :val1")
                .withExpressionAttributeValues(eav);

        List<Subscription> listOfSubscription = mapper.scan(Subscription.class, scanExpression);
        return listOfSubscription;
    }
}

