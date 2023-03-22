package com.jentsch.service;

import co.elastic.clients.elasticsearch._types.Result;
import com.jentsch.model.Category;

import java.io.IOException;

public interface CategoryService {

//    Product fetchProductById(Long id) throws RecordNotFoundException, IOException;

    Result saveCategory(Category category) throws IOException;

//    Result deleteProductById(Long id) throws IOException;
//
//    List<Product> fetchAllProducts() throws IOException;
//
//    boolean bulkSaveProducts(List<Category> products) throws IOException;
//
//    List<Product> fetchProducts(Category category) throws IOException;
}
