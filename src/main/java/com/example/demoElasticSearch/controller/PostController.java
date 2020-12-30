package com.example.demoElasticSearch.controller;

import com.example.demoElasticSearch.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping(value ="/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPost() throws IOException {
        postService.savePost();
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost() throws IOException {
        postService.updatePost();
    }

}
