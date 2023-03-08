package com.jentsch.skyer9;

import lombok.*;
import org.apache.http.HttpHost;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "elasticsearch")
@RequiredArgsConstructor
//elasticsearch:
//        host: 127.0.0.1
//        port: 9200
//        protocol: http

//elasticsearch:
//        schema: https
//        address: <公网 IP>:9200
//        username: elastic
//        password: elastic123
public class ElasticProperty {

    private List<ElasticProperty.Elasticsearch> server;

    @Getter
    public static class Elasticsearch {

        private String host;
        private int port;
        private String protocol;

        public HttpHost getHttpHost(){
            return new HttpHost(this.host,this.port,this.protocol);
        }
    }
}
