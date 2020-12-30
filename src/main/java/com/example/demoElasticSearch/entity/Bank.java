package com.example.demoElasticSearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "bank")
public class Bank {
    @Id
    private String id;
    @Field
    private String account_number;
    @Field
    private Integer balance;
    @Field
    private String firstname;
    @Field
    private String lastname;
    @Field
    private Integer age;
    @Field
    private String gender;
    @Field
    private String address;
    @Field
    private String employer;
    @Field
    private String email;
    @Field
    private String city;
    @Field
    private String state;
}
