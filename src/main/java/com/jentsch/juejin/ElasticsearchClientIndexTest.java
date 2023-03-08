package com.jentsch.juejin;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.aha.es.pojo.AhaIndex;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

/**
 * 测试 elasticSearch client 索引相关的操作
 *
 * @author WT
 * @date 2021/12/28 16:27:45
 */
@Slf4j
@SpringBootTest
public class ElasticsearchClientIndexTest {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    // 创建索引 - 不指定 mapping
    @Test
    public void createIndex () throws IOException {
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices()
                .create(createIndexRequest ->
                        createIndexRequest.index("elasticsearch-client")
                );
        log.info("== {} 索引创建是否成功: {}", "elasticsearch-client", createIndexResponse.acknowledged());
    }

    // 创建索引 - 指定 mapping
    @Test
    public void createIndexWithMapping () throws IOException {

        CreateIndexResponse createIndexResponse = elasticsearchClient.indices()
                .create(createIndexRequest ->
                        createIndexRequest.index("elasticsearch-client")
                                // 用 lambda 的方式 下面的 mapping 会覆盖上面的 mapping
                                .mappings(
                                        typeMapping ->
                                                typeMapping.properties("name", objectBuilder ->
                                                        objectBuilder.text(textProperty -> textProperty.fielddata(true))
                                                ).properties("age", objectBuilder ->
                                                        objectBuilder.integer(integerNumberProperty -> integerNumberProperty.index(true))
                                                )
                                )
                );

        log.info("== {} 索引创建是否成功: {}", "elasticsearch-client", createIndexResponse.acknowledged());
    }

    // 判断索引是否存在
    @Test
    public void indexIsExist () throws IOException {
        BooleanResponse booleanResponse = elasticsearchClient.indices()
                .exists(existsRequest ->
                        existsRequest.index("elasticsearch-client")
                );

        log.info("== {} 索引创建是否存在: {}", "elasticsearch-client", booleanResponse.value());
    }

    // 查看索引的相关信息
    @Test
    public void indexDetail () throws IOException {
        GetIndexResponse getIndexResponse = elasticsearchClient.indices()
                .get(getIndexRequest ->
                        getIndexRequest.index("elasticsearch-client")
                );

        Map<String, Property> properties = getIndexResponse.get("elasticsearch-client").mappings().properties();

        for (String key : properties.keySet()) {
            log.info("== {} 索引的详细信息为: == key: {}, Property: {}", "elasticsearch-client", key, properties.get(key)._kind());
        }

    }

    // 删除索引
    @Test
    public void deleteIndex () throws IOException {
        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices()
                .delete(deleteIndexRequest ->
                        deleteIndexRequest.index("elasticsearch-client")
                );

        log.info("== {} 索引创建是否删除成功: {}", "elasticsearch-client", deleteIndexResponse.acknowledged());
    }

    @Test
    public void testRestClient () throws IOException {

        SearchResponse<AhaIndex> search = elasticsearchClient.search(s -> s.index("aha-batch")
                        .query(q -> q.term(t -> t
                                .field("name")
                                .value(v -> v.stringValue("1aha"))
                        )),
                AhaIndex.class);

        for (Hit<AhaIndex> hit: search.hits().hits()) {
            log.info("== hit: {}", hit.source());
        }

    }

}
