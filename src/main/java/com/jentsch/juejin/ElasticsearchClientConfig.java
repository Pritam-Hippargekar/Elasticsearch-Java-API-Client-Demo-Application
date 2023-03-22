//package com.jentsch.juejin;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class ElasticsearchClientConfig {
//    private final ElasticSearchConfig elasticSearchConfig;
//
//    public ElasticsearchClientConfig (ElasticSearchConfig elasticSearchConfig) {
//        this.elasticSearchConfig = elasticSearchConfig;
//    }
//
//    @Bean
//    public RestClient restClient() {
//
//        List<HttpHost> hostLists = new ArrayList<>();
//        String[] hostArray = elasticSearchConfig.getAddress().split(",");
//        for (String temp : hostArray) {
//            String host = temp.split(":")[0];
//            String port = temp.split(":")[1];
//            hostLists.add(new HttpHost(host, Integer.parseInt(port), elasticSearchConfig.getSchema()));
//        }
//
//        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
//        RestClientBuilder builder = RestClient.builder(httpHost);
//        builder.setRequestConfigCallback(requestConfigBuilder -> {
//            requestConfigBuilder.setConnectTimeout(elasticSearchConfig.getConnectTimeout());
//            requestConfigBuilder.setSocketTimeout(elasticSearchConfig.getSocketTimeout());
//            requestConfigBuilder.setConnectionRequestTimeout(elasticSearchConfig.getConnectionRequestTimeout());
//            return requestConfigBuilder;
//        });
//
//        builder.setHttpClientConfigCallback(httpClientBuilder -> {
//            httpClientBuilder.setMaxConnTotal(elasticSearchConfig.getMaxConnectNum());
//            httpClientBuilder.setMaxConnPerRoute(elasticSearchConfig.getMaxConnectPerRoute());
//            return httpClientBuilder;
//        });
//
//        return builder.build();
//    }
//
//    @Bean
//    public ElasticsearchTransport elasticsearchTransport (RestClient restClient) {
//        return new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//    }
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient (ElasticsearchTransport transport) {
//        return new ElasticsearchClient(transport);
//    }
//
//}
