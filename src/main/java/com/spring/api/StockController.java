package com.spring.api;

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

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @ApiOperation("Load the entire stock table from a file.")
    @RequestMapping(value = "/stock/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFiles() {
        try {
            int sizeBeforeStatement = stockRepository.findAll().size();

            StockExcelManagement excelManagement = new StockExcelManagement();
            List<StockEntity> list = excelManagement.readTable();
            stockRepository.saveAll(list);

            int sizeAfterStatement = stockRepository.findAll().size();
            return ResponseEntity.ok("Stock Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire stock table to a file.")
    @RequestMapping(value = "/stock/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupFromFiles() {
        try {
            int sizeBeforeStatement = stockRepository.findAll().size();

            StockExcelManagement excelManagement = new StockExcelManagement();
            List<StockEntity> list = stockRepository.findAll();
            excelManagement.writeTable(list);

            int sizeAfterStatement = stockRepository.findAll().size();
            return ResponseEntity.ok("Stock Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
