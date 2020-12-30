package com.example.demoElasticSearch.repository;

import com.example.demoElasticSearch.entity.Bank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends ElasticsearchRepository<Bank, String> {
}
