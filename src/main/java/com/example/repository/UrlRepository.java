package com.example.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public String saveUrl(Url url) {
        dynamoDBMapper.save(url);
        return url.getHash();
    }

    public Url getUrlByHash(String hash) {
        return dynamoDBMapper.load(Url.class, hash);
    }

}
