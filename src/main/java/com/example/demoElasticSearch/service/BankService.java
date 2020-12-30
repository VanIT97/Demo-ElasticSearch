package com.example.demoElasticSearch.service;

import com.example.demoElasticSearch.entity.Bank;
import com.example.demoElasticSearch.repository.BankRepo;
import com.example.demoElasticSearch.repository.IBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    @Autowired
    IBankRepository bankRepository;

    @Autowired
    BankRepo bankRepo;

    public List<Bank> getBankList() throws IOException {
        return bankRepository.findAllBankDetailsFromElastic();
    }

    public List<Bank> getBankByName(String lastName) throws IOException {
        return bankRepository.findAllBankDataByLastNameFromElastic(lastName);
    }

    public Bank findBankById(String id) throws IOException {
        return bankRepository.findBankById(id);
    }

    public Bank getSourceBank(String id) throws IOException {
        return bankRepository.getSourceBank(id);
    }

    public boolean checkExistBank(String id) throws IOException {
        return bankRepository.checkExistBank(id);
    }

    public Iterable<Bank> getAll(){
        return bankRepo.findAll();
    }

}
