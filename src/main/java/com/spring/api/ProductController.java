package com.spring.api;

import com.spring.database.ProductRepository;
import com.spring.model.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param name is the primary key of the product
     * @return http 200 with the product or http 400 when product was not present
     */
    @ApiOperation("Request to get a product by it's name.")
    @RequestMapping(value = "/product",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@RequestParam(value = "name", defaultValue = "Apfel") String name) {
        return productRepository.findById(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @return http 200 with list of all products (can be empty)
     */
    @ApiOperation("Request to get a product by it's name.")
    @RequestMapping(value = "/product/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    /**
     * @param request json with product structure
     * @return http 200 at successful creation or http 400 when failing
     */
    @ApiOperation("Request to add a new product to the database.")
    @RequestMapping(value = "/product/add",
            method = RequestMethod.POST)
    public ResponseEntity<String> addProduct(@RequestBody Product request) {
        try {
            int sizeBeforeStatement = productRepository.findAll().size();

            Product product = new Product();
            product.setName(request.getName());
            product.setCategory(request.getCategory());
            product.setPackageGram(request.getPackageGram());
            product.setKCal(request.getKCal());
            product.setCarbohydrates(request.getCarbohydrates());
            product.setProtein(request.getProtein());
            product.setFat(request.getFat());
            product.setUnit(request.getUnit());
            productRepository.save(product);

            int sizeAfterStatement = productRepository.findAll().size();
            return ResponseEntity.ok("Product-Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
