package com.jentsch.config;

//import java.time.format.DateTimeFormatter;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//@Configuration
//public class JacksonConfiguration {
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return builder -> {
//            // formatter
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            // deserializers
//            builder.deserializers(new LocalDateDeserializer(dateFormatter));
//            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
//            // serializers
//            builder.serializers(new LocalDateSerializer(dateFormatter));
//            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
//        };
//    }
//}



//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//@Configuration
//class WebConfig {
//    @Bean
//    @Primary
//    public ObjectMapper customJson(){
//        return new Jackson2ObjectMapperBuilder()
//                .indentOutput(true)
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                .propertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE)
//                .build();
//    }
//}