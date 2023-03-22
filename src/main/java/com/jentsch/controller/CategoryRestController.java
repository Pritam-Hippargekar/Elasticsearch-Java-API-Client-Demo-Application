package com.jentsch.controller;

import co.elastic.clients.elasticsearch._types.Result;
import com.jentsch.exception.InvalidRecordException;
import com.jentsch.model.Category;
import com.jentsch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/")
//    public List<Product> getAllProducts()            throws IOException    {
//        List<Product> products = productService.fetchAllProducts();
//        return products;
//    }

//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable("id") final Long id)            throws IOException    {
//        if (id == null) {
//            throw new RecordNotFoundException("Please enter a valid 'id'");
//        }
//        return productService.fetchProductById(id);
//    }

//    @PostMapping("/find")
//    public List<Product> fetchProducts(@RequestBody Product product)            throws IOException    {
//        int anz = 1000;
//        return productService.fetchProducts(product);
//    }

    @PostMapping("/post")
    public Result saveProduct(@RequestBody Category category)  throws IOException    {
        if (category.getCategoryId() == null) {
            throw new InvalidRecordException("Please enter a valid 'id'");
        }
        Result result = categoryService.saveCategory(category);
        return result;
    }

//    @PostMapping("/all")
//    public Boolean saveProducts(@RequestBody List<Product> products)            throws IOException    {
//        Boolean result = productService.bulkSaveProducts(products);
//        return result;
//    }

//    @DeleteMapping("/{id}")
//    public Result deleteProductById(@PathVariable("id") final Long id)            throws IOException    {
//        return productService.deleteProductById(id);
//    }
}
