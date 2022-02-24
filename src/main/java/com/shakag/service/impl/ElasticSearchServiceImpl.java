package com.shakag.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakag.service.ElasticSearchService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private final RestHighLevelClient restHighLevelClient;
    private final RestClient restClient;

    /**
     * 通过restClient分词
     * @throws Exception IO
     */
    @Override
    public void ikParticiple() throws Exception{
        //创建分词请求
        Request request = new Request("GET", "_analyze");

        //添加请求体数据
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("analyzer", "ik_smart");  //ik_max_word   //ik_smart
        hashMap.put("text", "你好，我是中国人");
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(hashMap);
        request.setJsonEntity(value);

        //发送请求
        Response response = restClient.performRequest(request);

        //获取返回对象中的http实体
        HttpEntity entity = response.getEntity();

        //对http实体进行解析
        InputStream inputStream = entity.getContent();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        String s = new String(bytes);
        HashMap result = objectMapper.readValue(s, HashMap.class);

        //输出
        System.out.println("result = " + result);
    }
}
