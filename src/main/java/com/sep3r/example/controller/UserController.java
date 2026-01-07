package com.sep3r.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final DynamoDbClient dynamoDbClient;

    @PostMapping
    public String saveUser(@RequestBody Map<String, String> user) {
        dynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("users")
                .item(Map.of(
                        "id", AttributeValue.fromS(user.get("id")),
                        "name", AttributeValue.fromS(user.get("name"))
                ))
                .build());
        return "User saved";
    }
}
