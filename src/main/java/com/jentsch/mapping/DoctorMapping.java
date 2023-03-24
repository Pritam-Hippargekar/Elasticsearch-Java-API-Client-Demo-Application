//package com.jentsch.mapping;
//
//import co.elastic.clients.elasticsearch._types.AcknowledgedResponse;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import org.elasticsearch.client.RequestOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//https://lovemesomecoding.com/elasticsearch/elasticsearch-mapping
//import static co.elastic.clients.elasticsearch.indices.get.Feature.Settings;
//
//@Component
//public class DoctorMapping {
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    public boolean setUpIndex() {
//        String indexName = "doctors";
//
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
//        try {
//            AcknowledgedResponse deleteIndexResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//
//            CreateIndexRequest request = new CreateIndexRequest(indexName);
//
//            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));
//
//            XContentBuilder builder = XContentFactory.jsonBuilder();
//            builder.startObject();
//            {
//                builder.startObject("properties");
//                {
//                    builder.startObject("locations");
//                    {
//                        builder.field("type", "geo_point");
//                    }
//                    builder.endObject();
//
//                    builder.startObject("addresses");
//                    {
//                        builder.field("type", "nested");
//                    }
//                    builder.endObject();
//
//                    builder.startObject("specialities");
//                    {
//                        builder.field("type", "nested");
//                    }
//                    builder.endObject();
//
//                }
//                builder.endObject();
//            }
//            builder.endObject();
//            request.mapping(builder);
//
//            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//}
