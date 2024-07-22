//package com.cyyaw.elasticsearch;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//
//@Configuration
//public class ElasticsearchConfig {
//
//    @Bean
//    public RestHighLevelClient client() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("node1:9200", "node2:9200", "node3:9200")
//                .withBasicAuth("your_username", "your_password")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }
//
//    @Bean
//    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
//        return new ElasticsearchRestTemplate(client());
//    }
//}
