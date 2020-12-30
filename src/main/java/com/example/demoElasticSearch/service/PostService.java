package com.example.demoElasticSearch.service;

import com.example.demoElasticSearch.repository.IBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PostService {
    @Autowired
    IBankRepository bankRepository;

    public void savePost() throws IOException{
        bankRepository.createPost();
    }

    public void updatePost() throws IOException{
        bankRepository.updatePost();
    }
}
