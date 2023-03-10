package com.jentsch.connector;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.jentsch.exception.RecordNotFoundException;
import com.jentsch.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class JavaClientProductConnector {

    @Value("${elasticsearch.product.index}")
    private String index;

    @Autowired
    private ElasticsearchClient client;

    public Result saveProduct(Product product)     throws IOException    {
        IndexRequest<Product> request = IndexRequest.of(i->
                i.index(index).id(String.valueOf(product.getId())).document(product));
        IndexResponse response = client.index(request);
        Result result = response.result();
        return result;
    }

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

    public boolean bulkSaveProducts(List<Product> productList)     throws IOException    {
        BulkRequest.Builder builder = new BulkRequest.Builder();
        productList.stream().forEach(product ->
                builder.operations(op->
                        op.index(i->
                                i.index(index)
                                        .id(String.valueOf(product.getId()))
                                        .document(product)))
        );
        BulkResponse bulkResponse = client.bulk(builder.build());
        return !bulkResponse.errors();
    }

    public Product fetchProductById(Long id)   throws RecordNotFoundException, IOException    {
        GetResponse<Product> response = client
                .get(req-> req.index(index).id(String.valueOf(id)),Product.class);
        if(!response.found()) {
            throw new RecordNotFoundException("Product with ID" + id + " not found!");
        }
        return response.source();
    }

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

    public List<Product> fetchProducts(Product product)   throws IOException   {
        SearchResponse<Product> response = client.search(s -> s
                        .index(index)
                        .query(q -> q.match(t -> getQuery(t, product))), Product.class);

        // TotalHits total = response.hits().total();
        // boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
        List<Hit<Product>> hits = response.hits().hits();
        List<Product> products = new ArrayList<>();
        for (Hit<Product> hit: hits) {
            Product item = hit.source();
            products.add(item);
        }
        return products;
    }

    public Result deleteProductById(Long id)  throws IOException    {
        DeleteRequest request = DeleteRequest.of(req-> req.index(index).id(String.valueOf(id)));
        DeleteResponse response = client.delete(request);
        return response.result();
    }

//    public String deleteDocumentById(String productId) throws IOException {
//        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));
//        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
//        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
//            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
//        }
//        System.out.println("Product not found");
//        return new StringBuilder("Product with id " + deleteResponse.id() + " does not exist.").toString();
//    }

    public List<Product> fetchAllProducts()  throws IOException    {
        SearchResponse<Product> productSearchResponse =
                client.search(req-> req.index(index), Product.class);
        return productSearchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

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

    private static MatchQuery.Builder getQuery(MatchQuery.Builder builder, Product product) {
        Map<String, Object> conditionMap = Product.getConditionMap(product);
        for (Map.Entry<String, Object> entry: conditionMap.entrySet()) {
            if (entry.getValue() != null) {
                builder.field(entry.getKey()).query(entry.getValue().toString());
            }
        }
        return builder;
    }

    private static Query termQuery(String field, Object value){
        QueryVariant queryVariant = new TermQuery.Builder().caseInsensitive(true).field(field).value(value.toString()).build();
        return new Query(queryVariant);
    }
}