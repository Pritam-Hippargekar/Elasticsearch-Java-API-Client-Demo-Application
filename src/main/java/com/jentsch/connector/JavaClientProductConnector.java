package com.jentsch.connector;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.mapping.*;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.json.JsonpMapper;
import com.jentsch.exception.RecordNotFoundException;
import com.jentsch.model.Category;
import com.jentsch.model.Product;
import com.jentsch.utils.Constant;
import jakarta.json.stream.JsonParser;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class JavaClientProductConnector {

    @Value("${elasticsearch.product.index}")
    private String index;

    @Value("${elasticsearch.product.type}")
    private String type;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public Result saveProduct(Category category) throws IOException {
//        this.creatIndex();
        IndexRequest<Category> request = IndexRequest.of(i-> i
                        .index(index)
                        .id(String.valueOf(category.getCategoryId()))
                        .document(category)
                        .refresh(Refresh.True)); // Make it visible for search
        IndexResponse response = elasticsearchClient.index(request);
        Result result = response.result();
        return result;
    }

    public void creatIndex() {
        try {
            String indexName = Constant.INDEX_NAME;
            String indexType = Constant.INDEX_TYPE;
            Integer numberOfShards = Constant.NO_OF_SHARDS;
            Integer numberOfReplicas = Constant.NO_OF_REPLICAS;

            IndexSettings indexSettings = new IndexSettings.Builder()
                    .numberOfReplicas(String.valueOf(numberOfReplicas))
                    .numberOfShards(String.valueOf(numberOfShards))
                    .refreshInterval(new Time.Builder().time("5s").build())
                    .build();

//            String mappingPath = "yourPath/yourJsonFile.json";
//            JsonpMapper mapper = elasticsearchClient._transport().jsonpMapper();
//            JsonParser parser = mapper.jsonProvider()
//                    .createParser(new StringReader(
//                            Files.readString(new ClassPathResource(mappingPath).getFile().toPath(), Charsets.UTF_8)));
//            client.indices()
//                    .create(createIndexRequest -> createIndexRequest.index(indexName)
//                            .mappings(TypeMapping._DESERIALIZER.deserialize(parser, mapper)));



            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                    .index(indexName)
                    .settings(indexSettings)
                    .build();

            CreateIndexResponse createResponse = elasticsearchClient.indices().create(createIndexRequest);
            boolean acknowledged = createResponse.acknowledged();

            System.out.println("createIndex result: " + acknowledged);

        } catch (IOException e) {
            e.printStackTrace();
                Map<String, Property> propertyMap = new HashMap<>();
//                propertyMap.put("id", new Property(new NestedProperty.Builder().properties().build()));
//            propertyMap.put("date", new Property(new DateProperty.Builder().index(true).store(true).build()));
                propertyMap.put("id", new Property(new LongNumberProperty.Builder().index(true).store(true).build()));
                propertyMap.put("active", new Property(new BooleanProperty.Builder().index(true).store(true).build()));
                propertyMap.put("name", new Property(new TextProperty.Builder().index(true).analyzer("ik_max_word").store(true).build()));
        }
    }



    public boolean createIndex(String indexName, String aliasesName, int numOfShards,int numOfReplicas, Map<String, Property> properties) {
        try {
            TypeMapping typeMapping = new TypeMapping.Builder().properties(properties).build();
            IndexSettings indexSettings = new IndexSettings.Builder().numberOfShards(String.valueOf(numOfShards)).numberOfReplicas(String.valueOf(numOfReplicas)).build();
            CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                    .index(indexName)
                    .aliases(aliasesName, new Alias.Builder().isWriteIndex(true).build())
                    .mappings(typeMapping)
                    .settings(indexSettings)
                    .build();
            CreateIndexResponse response = elasticsearchClient.indices().create(createIndexRequest);
            return response.acknowledged();
        } catch (IOException e) {
//            logger.error("There is an error while creating index", e);
        }
        return false;
    }


//    public IndexResponse createIndex(String index, String type, String id, Object source) throws Exception{
//        IndexRequest request = new IndexRequest(index, type, id).source(JSON.toJSONString(source), XContentType.JSON);
//        return client.index(request);
//    }

//    public String createOrUpdateDocument(Product product) throws IOException {
//        IndexResponse response = elasticsearchClient.index(i -> i
//                .index(indexName)
//                .id(product.getId())
//                .document(product) );
//        if (response.result().name().equals("Created")) {
//            return new StringBuilder("Document has been successfully created.").toString();
//        } else if (response.result().name().equals("Updated")) {
//            return new StringBuilder("Document has been successfully updated.").toString();
//        }
//        return new StringBuilder("Error while performing the operation.").toString();
//    }

//    public boolean bulkSaveProducts(List<Product> productList)     throws IOException    {
//        BulkRequest.Builder builder = new BulkRequest.Builder();
//        productList.stream().forEach(product ->
//                builder.operations(op->
//                        op.index(i->
//                                i.index(index)
//                                        .id(String.valueOf(product.getId()))
//                                        .document(product)))
//        );
//        BulkResponse bulkResponse = client.bulk(builder.build());
//        return !bulkResponse.errors();
//    }

//    public Product fetchProductById(Long id)   throws RecordNotFoundException, IOException    {
//        GetResponse<Product> response = client
//                .get(req-> req.index(index).id(String.valueOf(id)),Product.class);
//        if(!response.found()) {
//            throw new RecordNotFoundException("Product with ID" + id + " not found!");
//        }
//        return response.source();
//    }

//    public Product getDocumentById(String productId) throws IOException {
//        Product product = null;
//        GetResponse<Product> response = elasticsearchClient.get(g -> g
//                        .index(indexName)
//                        .id(productId), Product.class );
//        if (response.found()) {
//            product = response.source();
//            System.out.println("Product name " + product.getName());
//        } else {
//            System.out.println("Product not found");
//        }
//        return product;
//    }

//    public List<Product> fetchProducts(Product product)   throws IOException   {
//        SearchResponse<Product> response = client.search(s -> s
//                        .index(index)
//                        .query(q -> q.match(t -> getQuery(t, product))), Product.class);
//
//        // TotalHits total = response.hits().total();
//        // boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
//        List<Hit<Product>> hits = response.hits().hits();
//        List<Product> products = new ArrayList<>();
//        for (Hit<Product> hit: hits) {
//            Product item = hit.source();
//            products.add(item);
//        }
//        return products;
//    }

//    public Result deleteProductById(Long id)  throws IOException    {
//        DeleteRequest request = DeleteRequest.of(req-> req.index(index).id(String.valueOf(id)));
//        DeleteResponse response = client.delete(request);
//        return response.result();
//    }

//    public String deleteDocumentById(String productId) throws IOException {
//        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));
//        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
//        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
//            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
//        }
//        System.out.println("Product not found");
//        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();
//    }

//    public List<Product> fetchAllProducts()  throws IOException    {
//        SearchResponse<Product> productSearchResponse =
//                client.search(req-> req.index(index), Product.class);
//        return productSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
//    }

//    public List<Product> searchAllDocuments() throws IOException {
//        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
//        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Product.class);
//        List<Hit> hits = searchResponse.hits().hits();
//        List<Product> products = new ArrayList<>();
//        for (Hit object : hits) {
//            System.out.print(((Product) object.source()));
//            products.add((Product) object.source());
//        }
//        return products;
//    }

//    private static MatchQuery.Builder getQuery(MatchQuery.Builder builder, Product product) {
//        Map<String, Object> conditionMap = Product.getConditionMap(product);
//        for (Map.Entry<String, Object> entry: conditionMap.entrySet()) {
//            if (entry.getValue() != null) {
//                builder.field(entry.getKey()).query(entry.getValue().toString());
//            }
//        }
//        return builder;
//    }
//
//    private static Query termQuery(String field, Object value){
//        QueryVariant queryVariant = new TermQuery.Builder().caseInsensitive(true).field(field).value(value.toString()).build();
//        return new Query(queryVariant);
//    }



//    CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName)
//            .settings(Settings.builder()
//                    .put("index.number_of_shards", NO_OF_SHARDS)
//                    .put("index.number_of_replicas", NO_OF_REPLICAS)
//                    .build());
//    OR request.mapping(TYPE, mapping, XContentType.JSON);
//   client.indices().create(createIndexRequest, DEFAULT);


}