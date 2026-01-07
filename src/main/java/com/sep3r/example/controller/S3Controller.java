package com.sep3r.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Client s3Client;

    @PostMapping("/bucket/{name}")
    public String createBucket(@PathVariable String name) {
        s3Client.createBucket(CreateBucketRequest.builder().bucket(name).build());
        return "Bucket created: " + name;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String bucket,
                         @RequestParam String key,
                         @RequestBody String body) {

        s3Client.putObject(
                PutObjectRequest.builder().bucket(bucket).key(key).build(),
                RequestBody.fromString(body)
        );
        return "File uploaded";
    }
}
