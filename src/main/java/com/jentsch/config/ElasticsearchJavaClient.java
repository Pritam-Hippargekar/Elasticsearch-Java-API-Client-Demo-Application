package com.jentsch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("elasticsearchclient")
@Getter
@Setter
public class ElasticsearchJavaClient {

    private String hostName;
    private int port;
    private String username;
    private String password;

//    ObjectMapper()
//            .findAndRegisterModules()
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        return objectMapper;
//    }

    @Bean
    public ElasticsearchClient getElasticSearchJavaApiClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
System.out.println(hostName);
        System.out.println(port);
        System.out.println(username);
        System.out.println(password);
//        RestClientBuilder builder = RestClient.builder(httpHost)
//                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                        .setSSLContext(sslContext)
//                        .setDefaultCredentialsProvider(credentialsProvider)
//                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE));
// OR
        RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port))
                .setHttpClientConfigCallback(httpClientBuilder ->  httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
// OR
//        RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port))
//                .setHttpClientConfigCallback(httpClientBuilder -> {
//                    httpClientBuilder.disableAuthCaching();  //Disable preemptive authentication
//                   return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
//                });

        // Create the low-level client
        RestClient restClient = builder.build();
        ObjectMapper mapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(mapper));

        // And create the API client
        return new ElasticsearchClient(transport);
    }

//    @Bean
//    public RestClient getRestClient() {
//        RestClient restClient = RestClient.builder(
//                new HttpHost("localhost", 9200)).build();
//        return restClient;
//    }
//
//    @Bean
//    public  ElasticsearchTransport getElasticsearchTransport() {
//        return new RestClientTransport(
//                getRestClient(), new JacksonJsonpMapper());
//    }
//
//
//    @Bean
//    public ElasticsearchClient getElasticsearchClient(){
//        ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
//        return client;
//    }

//    ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//            objectMapper.registerModule(new JavaTimeModule());



//    https://codecurated.com/blog/how-to-connect-java-with-elasticsearch/
//https://sergiiblog.com/symfony-elasticsearch/
//    https://juejin.cn/post/7120587835358314504
//https://juejin.cn/post/7174671838126014522
//    https://newsn.net/say/elastic-plugin-analysis-ik.html
//https://blog.microideation.com/2018/09/21/adding-nested-fields-objects-in-array-query-support-to-kibana-using-knql-plugin/
//    https://juejin.cn/post/7084951356569714724
//https://blog.csdn.net/wtl1992/article/details/123144901
//https://www.seaxiang.com/blog/ab3d3a88418643cdbfc2c8bfd6729190
//    https://git.lrting.top/xiaozhch5/springboot-elasticsearch/-/blob/master/src/main/java/com/zh/ch/springboot/elasticsearch/service/ElasticsearchServiceImpl.java
//    elasticsearch.hosts=10.0.2.9:9200,10.0.2.78:9200,10.0.2.211:9200
//    elasticsearch.username=elastic
//    elasticsearch.password=elastic
//    elasticsearch.connection.timeout=10000
//    elasticsearch.socket.timeout=10000
//    elasticsearch.connection.request.timeout=10000

//    @Value("${elasticsearch.hosts}")
//    public String elasticsearchHost;
//
//    @Value("${elasticsearch.username}")
//    public String elasticsearchUsername;
//
//    @Value("${elasticsearch.password}")
//    public String elasticsearchPassword;
//
//    @Value("${elasticsearch.connection.timeout}")
//    public int elasticsearchConnectionTimeout;
//
//    @Value("${elasticsearch.socket.timeout}")
//    public int elasticsearchSocketTimeout;
//
//    @Value("${elasticsearch.connection.request.timeout}")
//    public int getElasticsearchConnectionRequestTimeout;
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        RestClient restClient = RestClient.builder(getESHttpHosts()).setRequestConfigCallback(requestConfigBuilder -> {
//            requestConfigBuilder.setConnectTimeout(elasticsearchConnectionTimeout);
//            requestConfigBuilder.setSocketTimeout(elasticsearchSocketTimeout);
//            requestConfigBuilder.setConnectionRequestTimeout(getElasticsearchConnectionRequestTimeout);
//            return requestConfigBuilder;
//        }).setFailureListener(new RestClient.FailureListener() {
//            @Override
//            public void onFailure(Node node) {
//                logger.error(node);
//            }
//        }).setHttpClientConfigCallback(httpClientBuilder -> {
//            httpClientBuilder.disableAuthCaching();
//            return getHttpAsyncClientBuilder(httpClientBuilder);
//        }).build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
//
//     127.0.0.1:9201,127.0.0.1:9202,127.0.0.1:9203
//    private HttpHost[] getESHttpHosts() {
//        String[] hosts = elasticsearchHost.split(",");
//        HttpHost[] httpHosts = new HttpHost[hosts.length];
//        for (int i = 0; i < httpHosts.length; i++) {
//            String host = hosts[i];
//            host = host.replaceAll("http://", "").replaceAll("https://", "");
//            Assert.isTrue(host.contains(":"), String.format("your host %s format error , Please refer to [ 127.0.0.1:9200 ] ", host));
//            httpHosts[i] = new HttpHost(host.split(":")[0], Integer.parseInt(host.split(":")[1]), "http");
//        }
//        return httpHosts;
//    }
//
//    private HttpAsyncClientBuilder getHttpAsyncClientBuilder(HttpAsyncClientBuilder httpClientBuilder) {
//        if (StringUtils.isEmpty(elasticsearchUsername) || StringUtils.isEmpty(elasticsearchPassword)) {
//            return httpClientBuilder;
//        }
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchUsername, elasticsearchPassword));
//        httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//        return httpClientBuilder;
//    }

}
