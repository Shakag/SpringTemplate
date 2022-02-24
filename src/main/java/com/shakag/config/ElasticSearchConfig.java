package com.shakag.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        HttpHost httpHost = new HttpHost("192.168.10.129", 9200);
        RestClientBuilder builder = RestClient.builder(httpHost);

        //创建ES客户端并返回
        return new RestHighLevelClient(builder);
    }
}