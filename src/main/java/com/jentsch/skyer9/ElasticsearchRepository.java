//package com.jentsch.skyer9;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch._types.mapping.Property;
//import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
//import co.elastic.clients.elasticsearch.core.IndexRequest;
//import co.elastic.clients.elasticsearch.core.IndexResponse;
//import co.elastic.clients.elasticsearch.core.SearchRequest;
//import co.elastic.clients.elasticsearch.core.search.TotalHits;
//import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
//import co.elastic.clients.elasticsearch.indices.*;
//import co.elastic.clients.transport.endpoints.BooleanResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Optional;
//
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ElasticsearchRepository {
//
////    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private final ElasticsearchClient elasticsearchClient;
//
//    public boolean createIndex(CreateIndexProperty property) throws IOException {
//        CreateIndexRequest request = CreateIndexRequest.of(builder ->
//                builder.index(property.getIndexName()).withJson(property.getSettings())
//        );
//        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(request);
//        boolean acknowledged = createIndexResponse.acknowledged();
//        boolean shardsAcknowledged = createIndexResponse.shardsAcknowledged();
//        String index = createIndexResponse.index();
//        return Boolean.TRUE.equals(createIndexResponse.acknowledged());
//    }
//
////    public  void createIndex() throws IOException {
////        CreateIndexResponse response = client.indices().create(builder -> builder
////                .settings(indexSettingsBuilder -> indexSettingsBuilder. numberOfReplicas("1"). numberOfShards("2" ))
////                .mappings(typeMappingBuilder -> typeMappingBuilder
////                        .properties( "age", propertyBuilder -> propertyBuilder. integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder))
////                        .properties( "name", propertyBuilder -> propertyBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder))
////                        .properties( "poems", propertyBuilder -> propertyBuilder.text(textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_max_word" )) )
////                        .properties( "about", propertyBuilder -> propertyBuilder.text(textPropertyBuilder -> textPropertyBuilder.analyzer("ik_max_word").searchAnalyzer("ik_max_word" )) )
////                        .properties( "success", propertyBuilder -> propertyBuilder. text(textPropertyBuilder -> textPropertyBuilder. analyzer("ik_max_word"). searchAnalyzer("ik_max_word" )) )
////                )
////                .index(INDEX_NAME));
////        logger.info( "acknowledged={}" , response.acknowledged());
////    }
//
////    Fields can be added, existing fields can only modify the search_analyzer attribute of the field.
////    public  void modifyIndex() throws IOException {
////        PutMappingResponse response = client.indices().putMapping(typeMappingBuilder -> typeMappingBuilder
////                .index(INDEX_NAME)
////                .properties( "age", propertyBuilder -> propertyBuilder. integer(integerNumberPropertyBuilder -> integerNumberPropertyBuilder))
////                .properties( "name", propertyBuilder -> propertyBuilder.keyword(keywordPropertyBuilder -> keywordPropertyBuilder))
////                .properties( "poems", propertyBuilder -> propertyBuilder. text(textPropertyBuilder -> textPropertyBuilder. analyzer("ik_max_word"). searchAnalyzer("ik_smart")) )
////        );
////        logger.info( "acknowledged={}" , response.acknowledged());
////    }
//
////    Map<String, Property> propertyMap = new HashMap<>();
////    propertyMap.put("id", new Property(new LongNumberProperty.Builder().index(true).store(true).build()));
////    propertyMap.put("name", new Property(new TextProperty.Builder().index(true).analyzer("ik_max_word").store(true).build()));
//
//    public boolean createIndex(String indexName, int numberOfShards,int numberOfReplicas, Map<String, Property> properties) throws IOException {
//        TypeMapping typeMapping = new TypeMapping.Builder()
//                .properties(properties)
//                .build();
//
//        IndexSettings indexSettings = new IndexSettings.Builder()
//                .numberOfShards(String.valueOf(numberOfShards))
//                .numberOfReplicas(String.valueOf(numberOfReplicas))
//                .build();
//
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
//                .index(indexName)
//                .mappings(typeMapping)
//                .settings(indexSettings)
//                .build();
//        try {
//            return Optional
//                    .ofNullable(elasticsearchClient.indices().create(createIndexRequest).acknowledged())
//                    .orElse(Boolean.FALSE);
//        } catch (IOException e) {
//            log.error("create Index error", e);
//        }
//        return false;
//    }
//
//    //https://www.cnblogs.com/BNTang/articles/16100912.html
//    public boolean deleteIndex(String indexName) {
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest.Builder()
//                .index(indexName)
//                .build();
//        try {
//            DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices().delete(deleteIndexRequest);
//            return deleteIndexResponse.acknowledged();
//        } catch (IOException e) {
//            log.error("delete Index error", e);
//        }
//        return false;
//    }
//
//    public  void indexDetail() throws IOException {
//        GetIndexResponse response = elasticsearchClient.indices().get(builder -> builder.index(INDEX_NAME));
//        log.info(response.result().toString());
//    }
//
//    public boolean indexExists(String indexName) {
//        ExistsRequest existsRequest = new ExistsRequest.Builder().index(indexName).build();
//        try{
//            BooleanResponse booleanResponse = elasticsearchClient.indices().exists(existsRequest);
//            return booleanResponse.value();
//        }catch (IOException e) {
//            log.error("see Index Is No Exists error", e);
//        }
//        return false;
//    }
//
////    public long createOrUpdateDocument(ItemDocument itemDocument) throws IOException {
////        IndexRequest<ItemDocument> request = IndexRequest.of(builder -> builder
////                .index(IndexName.ITEM)
////                .id(itemDocument.getId().toString())
////                .document(itemDocument)
////        );
////        IndexResponse response = client.index(request);
////        return response.version();
////    }
//
////    public ItemDocument getDocument() throws IOException {
////        GetResponse<ItemDocument> response = client.get(builder -> builder
////                        .index(IndexName.ITEM)
////                        .id("1"),
////                ItemDocument.class
////        );
////        if (response.found()) {
////            return response.source();
////        } else {
////            logger.info ("Document not found");
////        }
////        return null;
////    }
//
////    public List<Hit<ItemDocument>> search() throws IOException {
////        String searchText = "test";
////        SearchResponse<ItemDocument> response = client.search(s -> s
////                        .index(IndexName.ITEM)
////                        .query(q -> q
////                                .match(t -> t
////                                        .field("mimetype")
////                                        .query(searchText)
////                                )
////                        ),
////                ItemDocument.class
////        );
////        TotalHits total = response.hits().total();
////        assert total != null;
////        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
////        if (isExactResult) {
////            logger.info("There are " + total.value() + " results");
////            Query query = SearchRequest.of(builder -> builder
////                    .index(IndexName.ITEM)
////                    .query(q -> q
////                            .match(t -> t
////                                    .field("mimetype")
////                                    .query(searchText)
////                            )
////                    )).query();
////            logger.info("query : {}", query);
////            logger.info("response : {}", response);
////            logger.info("response.hits() : {}", response.hits());
////        } else {
////            logger.info("There are more than " + total.value() + " results");
////        }
////        return response.hits().hits();
////    }
//
////    public T getById(String index, String id, Class<T> clazz) {
////        GetRequest getRequest = new GetRequest.Builder()
////                .index(index)
////                .id(id)
////                .build();
////        try {
////            return this.elasticsearchClient.get(getRequest, clazz)
////                    .source();
////        } catch (IOException e) {
////            logger.error("get By Id error", e);
////        }
////        return null;
////    }
//
////    public List<T> getByIdList(String index, List<String> idList, Class<T> clazz) {
////        try {
////            List<T> tList = new ArrayList<>(idList.size());
////            for (String id : idList) {
////                GetRequest getRequest = new GetRequest.Builder()
////                        .index(index)
////                        .id(id)
////                        .build();
////                T source = this.elasticsearchClient.get(getRequest, clazz)
////                        .source();
////                tList.add(source);
////            }
////            return tList;
////        } catch (IOException e) {
////            logger.error("get By Id List error", e);
////        }
////        return null;
////    }
//
////    public List<T> searchByPages(String index, Integer pageNo, Integer pageSize, Class<T> clazz) {
////        SearchRequest searchRequest = new SearchRequest.Builder()
////                .index(Collections.singletonList(index))
////                .from(pageNo)
////                .size(pageSize)
////                .build();
////        List<T> res = new ArrayList<>();
////        try {
////            SearchResponse<T> searchResponse = this.elasticsearchClient.search(searchRequest, clazz);
////            HitsMetadata<T> hitsMetadata = searchResponse.hits();
////            hitsMetadata.hits().forEach(action -> res.add(action.source()));
////            return res;
////        } catch (IOException e) {
////            logger.error("search By Pages error", e);
////        }
////        return null;
////    }
//
////    public List<T> searchByQuery(String index, String queryString, Integer pageNo, Integer pageSize, Class<T> clazz) {
////        QueryStringQuery stringQuery = new QueryStringQuery.Builder()
////                .fields("name", "description")
////                .query(queryString)
////                .build();
////        Query query = new Query.Builder()
////                .queryString(stringQuery)
////                .build();
////        SearchRequest searchRequest = new SearchRequest.Builder()
////                .index(index)
////                .from(pageNo)
////                .size(pageSize)
////                .query(query)
////                .build();
////        try {
////            return this.elasticsearchClient.search(searchRequest, clazz)
////                    .hits().hits().stream()
////                    .map(Hit::source)
////                    .collect(Collectors.toList());
////        } catch (IOException e) {
////            logger.error("search By Query error", e);
////        }
////        return null;
////    }
//
////    public List<T> searchByQueryHighlight(String index, String queryString, Integer pageNo, Integer pageSize, Class<T> clazz) {
////        QueryStringQuery stringQuery = new QueryStringQuery.Builder()
////                .fields("name", "description")
////                .query(queryString)
////                .build();
////        Query query = new Query.Builder()
////                .queryString(stringQuery)
////                .build();
////        HighlightField highlightField = new HighlightField.Builder()
////                .matchedFields("name")
////                .preTags("<span style=\"color:red\">")
////                .postTags("</span>")
////                .build();
////        Highlight highlight = new Highlight.Builder()
////                .fields("name", highlightField)
////                .requireFieldMatch(false)
////                .build();
////        SearchRequest searchRequest = new SearchRequest.Builder()
////                .index(index)
////                .from(pageNo)
////                .size(pageSize)
////                .query(query)
////                .highlight(highlight)
////                .build();
////        try {
////            return this.elasticsearchClient.search(searchRequest, clazz)
////                    .hits()
////                    .hits()
////                    .stream()
////                    .map(mapper -> {
////                        String name = mapper.highlight()
////                                .get("name")
////                                .get(0);
////                        Goods goods = (Goods) mapper.source();
////                        Goods anElse = Optional.ofNullable(goods)
////                                .orElse(new Goods());
////                        anElse.setName(name);
////                        return mapper.source();
////                    }).collect(Collectors.toList());
////        } catch (IOException e) {
////            logger.error("search By Query Highlight error", e);
////        }
////        return null;
////    }
//}
