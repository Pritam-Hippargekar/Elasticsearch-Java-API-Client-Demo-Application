package com.jentsch.service.impl;

import co.elastic.clients.elasticsearch._types.Result;
import com.jentsch.connector.JavaClientProductConnector;
import com.jentsch.model.Category;
import com.jentsch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private JavaClientProductConnector javaClientProductConnector;

//    @Override
//    public Product fetchProductById(Long id) throws RecordNotFoundException, IOException {
//        return javaClientProductConnector.fetchProductById(id);
//    }

    @Override
    public Result saveCategory(Category category) throws IOException {
        return javaClientProductConnector.saveProduct(category);
    }

//    @Override
//    public Result deleteProductById(Long id) throws IOException {
//        return javaClientProductConnector.deleteProductById(id);
//    }
//
//    @Override
//    public List<Product> fetchAllProducts() throws IOException {
//        return javaClientProductConnector.fetchAllProducts();
//    }
//
//    @Override
//    public boolean bulkSaveProducts(List<Product> products) throws IOException {
//        return javaClientProductConnector.bulkSaveProducts(products);
//    }
//
//    @Override
//    public List<Product> fetchProducts(Product product) throws IOException {
//        return javaClientProductConnector.fetchProducts(product);
//    }

}
