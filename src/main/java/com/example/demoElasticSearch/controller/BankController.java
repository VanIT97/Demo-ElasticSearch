package com.example.demoElasticSearch.controller;

import com.example.demoElasticSearch.entity.Bank;
import com.example.demoElasticSearch.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping(value ="/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Bank> getAllBank() throws IOException {
        return bankService.getAll();
    }

    @GetMapping(value ="/allBank", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAllUser() throws IOException {
        return bankService.getBankList();
    }

    @GetMapping(value ="/getByName/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAllUser(@PathVariable String lastName) throws IOException {
        return bankService.getBankByName(lastName);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank findById(@PathVariable String id) throws IOException{
        return bankService.findBankById(id);
    }

    @GetMapping(value = "/getSource/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank getSourceBank(@PathVariable String id) throws IOException{
        return bankService.getSourceBank(id);
    }

    @GetMapping(value = "/checkExist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkExists(@PathVariable String id) throws IOException{
        return bankService.checkExistBank(id);
    }


}
