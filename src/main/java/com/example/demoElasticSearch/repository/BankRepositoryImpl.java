package com.example.demoElasticSearch.repository;

import com.example.demoElasticSearch.config.FirstRestClient;
import com.example.demoElasticSearch.config.SecondRestClient;
import com.example.demoElasticSearch.entity.Bank;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class BankRepositoryImpl implements IBankRepository{
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    FirstRestClient client;

    @Autowired
    SecondRestClient client2;

//    RestHighLevelClient client = new RestHighLevelClient(
//            RestClient.builder(new HttpHost("localhost", 9200, "http")));



    public BankRepositoryImpl(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    @Override
    public void createPost() throws IOException{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1").source(jsonMap);
        IndexResponse indexResponse = client.client().index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void updatePost() throws IOException {
        UpdateRequest request = new UpdateRequest(
                "posts",
                "1");
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";
        request.doc(jsonString, XContentType.JSON);
        String json = "{\"created\":\"2017-01-01\"}";
        request.upsert(json, XContentType.JSON);
        UpdateResponse updateResponse = client.client().update(
                request, RequestOptions.DEFAULT);
        String index = updateResponse.getIndex();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {

        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

        }
    }

    @Override
    public Bank findBankById(String id) throws IOException {
        GetRequest getRequest = new GetRequest(
                "bank",
                id);
        GetResponse getResponse = client.client().get(getRequest, RequestOptions.DEFAULT);
        return objectMapper.convertValue(getResponse.getSourceAsMap(), Bank.class);
    }

    @Override
    public Bank getSourceBank(String id) throws IOException {
        GetSourceRequest getSourceRequest = new GetSourceRequest(
                "bank",
                "1");
        GetSourceResponse response =
                client.client().getSource(getSourceRequest, RequestOptions.DEFAULT);
        return objectMapper.convertValue(response.getSource(), Bank.class);
    }

    @Override
    public boolean checkExistBank(String id) throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");

        return client.client().exists(getRequest, RequestOptions.DEFAULT);
    }

    @Override
    public List<Bank> findAllBankDetailsFromElastic() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        List<Bank> bankList = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse =client.client().search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    bankList.add(objectMapper.convertValue(map, Bank.class));
                }
            }
            return bankList;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Bank> findAllBankDataByLastNameFromElastic(String lastName) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query((QueryBuilders.matchQuery("lastname", lastName)));
        searchRequest.source(searchSourceBuilder);
        List<Bank> bankList = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse =client.client().search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    bankList.add(objectMapper.convertValue(map, Bank.class));
                }
            }
            return bankList;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bank> findAllBankDataByLastNameAndAddressFromElstic(String lastName, String address) {
        return null;
    }
}
