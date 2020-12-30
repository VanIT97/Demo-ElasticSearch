package com.example.demoElasticSearch.repository;

import com.example.demoElasticSearch.entity.Bank;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface IBankRepository {
    void createPost() throws IOException;

    void updatePost() throws IOException;

    Bank findBankById(String id) throws IOException;

    List<Bank> findAllBankDetailsFromElastic() throws IOException;

    Bank getSourceBank(String id) throws IOException;

    boolean checkExistBank(String id) throws IOException;

    List<Bank> findAllBankDataByLastNameFromElastic(String lastName);

    List<Bank> findAllBankDataByLastNameAndAddressFromElstic(String lastName, String address);
}
