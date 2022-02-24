package com.shakag.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    private static final  HttpHost httpHost = new HttpHost("localhost", 1001);

    /**
     * ES高级客户端 RestHighLevelClient
     * @return RestHighLevelClient
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder builder = RestClient.builder(httpHost);

        //创建ES客户端并返回
        return new RestHighLevelClient(builder);
    }

    /**
     * ES低级客户端 LowLevelClient
     * @return LowLevelClient
     */
    @Bean
    public RestClient restClient(){
        RestClientBuilder builder = RestClient.builder(httpHost);

        //创建ES客户端并返回
        RestClient restClient = builder.build();
        return restClient;
    }
}