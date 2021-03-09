package com.spring.controller;

import com.spring.controller.utility.RepoBackup;
import com.spring.database.StockRepository;
import com.spring.database.backup.StockExcelManagement;
import com.spring.model.entity.StockEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    //=============== BACKUP ===========================================================================================

    @ApiOperation("Load the entire stock table from a file.")
    @RequestMapping(value = "/stock/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFile() {
        try {
            RepoBackup<StockEntity, StockRepository, StockExcelManagement> repoBackup
                    = new RepoBackup<>(stockRepository, new StockExcelManagement());
            return ResponseEntity.ok(repoBackup.loadBackup());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire stock table to a file.")
    @RequestMapping(value = "/stock/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupToFile() {
        try {
            RepoBackup<StockEntity, StockRepository, StockExcelManagement> repoBackup
                    = new RepoBackup<>(stockRepository, new StockExcelManagement());
            return ResponseEntity.ok(repoBackup.saveBackup());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
