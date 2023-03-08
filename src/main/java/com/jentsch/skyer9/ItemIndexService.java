package com.jentsch.skyer9;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ItemIndexService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ElasticsearchRepository repository;

    public boolean createIndex() throws IOException {

        CreateIndexProperty property = new CreateIndexProperty();
        ClassPathResource resource = new ClassPathResource("json/create_item_index.json");
        InputStream input = resource.getInputStream();
        property.setIndexName(IndexName.ITEM);
        property.setSettings(input);
        if (repository.indexExists(IndexName.ITEM)) {
            logger.debug("index exists");
            repository.deleteIndex(IndexName.ITEM);
            logger.debug("index deleted");
        }
        return repository.createIndex(property);
    }

//    public void addDocument() throws IOException {
//        ItemDocument itemDocument = new ItemDocument();
//        itemDocument.setId(1);
//        itemDocument.setMimetype("test");
//
//        long version = repository.createOrUpdateDocument(itemDocument);
//        logger.debug("Document version : {}", version);
//    }

//    public void getDocument() throws IOException {
//        ItemDocument itemDocument = repository.getDocument();
//
//        if (itemDocument != null) {
//            logger.info("Mimetype :  " + itemDocument.getMimetype());
//        }
//    }

//    public void search() throws IOException {
//        List<Hit<ItemDocument>> hits = repository.search();
//        for (Hit<ItemDocument> hit: hits) {
//            ItemDocument product = hit.source();
//            logger.info("Found Item " + product.getMimetype() + ", score " + hit.score());
//        }
//    }
}
