package com.jentsch.skyer9;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchClientConfig {

    private final ElasticProperty elasticProperty;

    @Bean
    public ElasticsearchClient getJavaApiClient() {

        HttpHost[] httpHost = new HttpHost[elasticProperty.getServer().size()];
        int idx = 0;
        for(ElasticProperty.Elasticsearch elasticsearch : elasticProperty.getServer()){
            httpHost[idx++] = elasticsearch.getHttpHost();
        }

        RestClient restClient = RestClient.builder(httpHost).build();

        ObjectMapper mapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(mapper));

        return new ElasticsearchClient(transport);
    }
}