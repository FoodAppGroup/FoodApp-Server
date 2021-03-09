package com.spring.api;

import com.spring.api.utility.RepoBackup;
import com.spring.api.utility.RepoLog;
import com.spring.database.ProductRepository;
import com.spring.database.backup.ProductExcelManagement;
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
        RepoLog<ProductRepository> repoLog = new RepoLog<>(productRepository);
        try {
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

            return ResponseEntity.ok(repoLog.getChangedSize());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //=============== BACKUP ===========================================================================================

    @ApiOperation("Load the entire product table from a file.")
    @RequestMapping(value = "/product/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFile() {
        try {
            RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                    = new RepoBackup<>(productRepository, new ProductExcelManagement());
            return ResponseEntity.ok(repoBackup.loadBackup());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire product table to a file.")
    @RequestMapping(value = "/product/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupToFile() {
        try {
            RepoBackup<Product, ProductRepository, ProductExcelManagement> repoBackup
                    = new RepoBackup<>(productRepository, new ProductExcelManagement());
            return ResponseEntity.ok(repoBackup.saveBackup());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
