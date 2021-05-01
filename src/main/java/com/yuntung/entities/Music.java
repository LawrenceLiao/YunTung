package com.yuntung.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@DynamoDBTable(tableName = "music")
public class Music {

    @DynamoDBAttribute(attributeName = "title")
    @DynamoDBHashKey
    private String title;

    @DynamoDBAttribute(attributeName = "artist")
    @DynamoDBRangeKey
    private String artist;

    @DynamoDBAttribute(attributeName = "web_url")
    private String webUrl;

    @DynamoDBAttribute(attributeName = "img_url")
    private String imgUrl;

    @DynamoDBAttribute(attributeName = "year")
    private String year;
}

