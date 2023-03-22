//package com.jentsch.juejin;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch._types.mapping.Property;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.core.search.Hit;
//import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
//import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
//import co.elastic.clients.transport.endpoints.BooleanResponse;
//import com.aha.es.pojo.AhaIndex;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Slf4j
//@SpringBootTest
//public class ElasticsearchClientIndexTest {
//    @Autowired
//    private ElasticsearchClient elasticsearchClient;
//
//    public void createIndex () throws IOException {
//        CreateIndexResponse createIndexResponse = elasticsearchClient.indices()
//                .create(createIndexRequest ->
//                        createIndexRequest.index("elasticsearch-client")
//                );
//        log.info("== {}: {}", "elasticsearch-client", createIndexResponse.acknowledged());
//    }
//
//    public void createIndexWithMapping () throws IOException {
//
//        CreateIndexResponse createIndexResponse = elasticsearchClient.indices()
//                .create(createIndexRequest ->
//                        createIndexRequest.index("elasticsearch-client")
//                                .mappings(
//                                        typeMapping ->
//                                                typeMapping.properties("name", objectBuilder ->
//                                                        objectBuilder.text(textProperty -> textProperty.fielddata(true))
//                                                ).properties("age", objectBuilder ->
//                                                        objectBuilder.integer(integerNumberProperty -> integerNumberProperty.index(true))
//                                                )
//                                )
//                );
//
//        log.info("== {} : {}", "elasticsearch-client", createIndexResponse.acknowledged());
//    }
//
//    public void indexIsExist () throws IOException {
//        BooleanResponse booleanResponse = elasticsearchClient.indices()
//                .exists(existsRequest ->
//                        existsRequest.index("elasticsearch-client")
//                );
//
//        log.info("== {}: {}", "elasticsearch-client", booleanResponse.value());
//    }
//
//    public void indexDetail () throws IOException {
//        GetIndexResponse getIndexResponse = elasticsearchClient.indices()
//                .get(getIndexRequest ->
//                        getIndexRequest.index("elasticsearch-client")
//                );
//
//        Map<String, Property> properties = getIndexResponse.get("elasticsearch-client").mappings().properties();
//
//        for (String key : properties.keySet()) {
//            log.info("== {}: == key: {}, Property: {}", "elasticsearch-client", key, properties.get(key)._kind());
//        }
//
//    }
//
//    public void deleteIndex () throws IOException {
//        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices()
//                .delete(deleteIndexRequest ->
//                        deleteIndexRequest.index("elasticsearch-client")
//                );
//
//        log.info("== {} : {}", "elasticsearch-client", deleteIndexResponse.acknowledged());
//    }
//
//    @Test
//    public void testRestClient () throws IOException {
//
//        SearchResponse<AhaIndex> search = elasticsearchClient.search(s -> s.index("aha-batch")
//                        .query(q -> q.term(t -> t
//                                .field("name")
//                                .value(v -> v.stringValue("1aha"))
//                        )),
//                AhaIndex.class);
//
//        for (Hit<AhaIndex> hit: search.hits().hits()) {
//            log.info("== hit: {}", hit.source());
//        }
//
//    }
//
//}
