package com.example.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Value("${aws.dynamodb.accesskey}")
    private String dynamodbAccessKey;

    @Value("${aws.dynamodb.secretkey}")
    private String dynamodbSecretKey;

    @Value("${aws.region}")
    private String dynamodbRegion;


    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard().withRegion(dynamodbRegion)
                .withCredentials(new AWSStaticCredentialsProvider(
                   new BasicAWSCredentials(dynamodbAccessKey,dynamodbSecretKey)))
                .build();
    }
}