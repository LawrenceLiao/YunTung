package com.yuntung.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yuntung.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public UserEntity add(UserEntity user) {
        mapper.save(user);
        return user;
    }

    public UserEntity findByEmail(String email) {
        UserEntity foundUser = mapper.load(UserEntity.class, email);
        return foundUser;
    }
}