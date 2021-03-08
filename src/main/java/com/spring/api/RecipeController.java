package com.spring.api;

import com.spring.database.RecipePartRepository;
import com.spring.database.RecipePlanningRepository;
import com.spring.database.RecipeRepository;
import com.spring.dataprovider.databaseBackup.RecipeExcelManagement;
import com.spring.dataprovider.databaseBackup.RecipePartExcelManagement;
import com.spring.dataprovider.databaseBackup.RecipePlanningExcelManagement;
import com.spring.model.entity.RecipeEntity;
import com.spring.model.entity.RecipePartEntity;
import com.spring.model.entity.RecipePlanningEntity;
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
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipePartRepository recipePartRepository;

    @Autowired
    private RecipePlanningRepository recipePlanningRepository;


    @ApiOperation("Load the entire recipe tables from the files.")
    @RequestMapping(value = "/recipe/backup/load",
            method = RequestMethod.POST)
    public ResponseEntity<String> loadBackupFromFiles() {
        try {
            int sizeBeforeStatement = recipeRepository.findAll().size();
            int sizeBeforeStatementPart = recipePartRepository.findAll().size();
            int sizeBeforeStatementPlanning = recipePlanningRepository.findAll().size();

            RecipeExcelManagement excelManagement1 = new RecipeExcelManagement();
            List<RecipeEntity> list1 = excelManagement1.readTable();
            recipeRepository.saveAll(list1);

            RecipePartExcelManagement excelManagement2 = new RecipePartExcelManagement();
            List<RecipePartEntity> list2 = excelManagement2.readTable();
            recipePartRepository.saveAll(list2);

            RecipePlanningExcelManagement excelManagement3 = new RecipePlanningExcelManagement();
            List<RecipePlanningEntity> list3 = excelManagement3.readTable();
            recipePlanningRepository.saveAll(list3);

            int sizeAfterStatement = recipeRepository.findAll().size();
            int sizeAfterStatementPart = recipePartRepository.findAll().size();
            int sizeAfterStatementPlanning = recipePlanningRepository.findAll().size();
            return ResponseEntity.ok("Recipe Table Sizes: Recipe (" + sizeBeforeStatement + " -> " + sizeAfterStatement + ") " +
                    "Parts: (" + sizeBeforeStatementPart + " -> " + sizeAfterStatementPart + ") " +
                    "Planning: (" + sizeBeforeStatementPlanning + " -> " + sizeAfterStatementPlanning + ")");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Saves the entire recipe tables to the files.")
    @RequestMapping(value = "/recipe/backup/save",
            method = RequestMethod.POST)
    public ResponseEntity<String> saveBackupFromFiles() {
        try {
            int sizeBeforeStatement = recipeRepository.findAll().size();
            int sizeBeforeStatementPart = recipePartRepository.findAll().size();
            int sizeBeforeStatementPlanning = recipePlanningRepository.findAll().size();

            RecipeExcelManagement excelManagement1 = new RecipeExcelManagement();
            List<RecipeEntity> list1 = excelManagement1.readTable();
            recipeRepository.saveAll(list1);

            RecipePartExcelManagement excelManagement2 = new RecipePartExcelManagement();
            List<RecipePartEntity> list2 = excelManagement2.readTable();
            recipePartRepository.saveAll(list2);

            RecipePlanningExcelManagement excelManagement3 = new RecipePlanningExcelManagement();
            List<RecipePlanningEntity> list3 = excelManagement3.readTable();
            recipePlanningRepository.saveAll(list3);

            int sizeAfterStatement = recipeRepository.findAll().size();
            int sizeAfterStatementPart = recipePartRepository.findAll().size();
            int sizeAfterStatementPlanning = recipePlanningRepository.findAll().size();
            return ResponseEntity.ok("Recipe Table Sizes: Recipe (" + sizeBeforeStatement + " -> " + sizeAfterStatement + ") " +
                    "Parts: (" + sizeBeforeStatementPart + " -> " + sizeAfterStatementPart + ") " +
                    "Planning: (" + sizeBeforeStatementPlanning + " -> " + sizeAfterStatementPlanning + ")");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
