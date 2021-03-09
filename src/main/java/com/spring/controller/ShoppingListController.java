package com.spring.controller;

import com.spring.database.ShoppingListRepository;
import com.spring.database.backup.ShoppingListExcelManagement;
import com.spring.model.entity.ShoppingListEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @ApiOperation("Load the entire shopping-list table from a file.")
    @RequestMapping(value = "/shopping-list/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFiles() {
        try {
            int sizeBeforeStatement = shoppingListRepository.findAll().size();

            ShoppingListExcelManagement excelManagement = new ShoppingListExcelManagement();
            List<ShoppingListEntity> list = excelManagement.readTable();
            shoppingListRepository.saveAll(list);

            int sizeAfterStatement = shoppingListRepository.findAll().size();
            return ResponseEntity.ok("Shopping List Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire shopping-list table to a file.")
    @RequestMapping(value = "/shopping-list/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupFromFiles() {
        try {
            int sizeBeforeStatement = shoppingListRepository.findAll().size();

            ShoppingListExcelManagement excelManagement = new ShoppingListExcelManagement();
            List<ShoppingListEntity> list = shoppingListRepository.findAll();
            excelManagement.writeTable(list);

            int sizeAfterStatement = shoppingListRepository.findAll().size();
            return ResponseEntity.ok("Shopping List Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
