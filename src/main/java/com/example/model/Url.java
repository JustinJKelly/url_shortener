package com.example.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "url-shortener-table-prod")
public class Url {

    @DynamoDBHashKey(attributeName = "url_hash")
    private String hash;

    @DynamoDBAttribute(attributeName = "url")
    private String url;


    @DynamoDBAttribute
    public String getUrl() {
        return url;
    }

    @DynamoDBAttribute
    public String getHash() {
        return hash;
    }

    @DynamoDBAttribute
    public void setUrl(String url) {
        this.url = url;
    }

    @DynamoDBAttribute
    public void setHash(String hash) {
        this.hash = hash;
    }

    // standard setters/constructors
}