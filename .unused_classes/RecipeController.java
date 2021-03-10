package com.spring.controller;

import com.spring.database.backup.RecipeExcelManagement;
import com.spring.database.backup.RecipePartExcelManagement;
import com.spring.database.backup.RecipePlanningExcelManagement;
import com.spring.database.repository.RecipePartRepository;
import com.spring.database.repository.RecipePlanningRepository;
import com.spring.database.repository.RecipeRepository;
import com.spring.model.entity.Recipe;
import com.spring.model.entity.RecipePart;
import com.spring.model.entity.RecipePlanning;
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
            List<Recipe> list1 = excelManagement1.readTable();
            recipeRepository.saveAll(list1);

            RecipePartExcelManagement excelManagement2 = new RecipePartExcelManagement();
            List<RecipePart> list2 = excelManagement2.readTable();
            recipePartRepository.saveAll(list2);

            RecipePlanningExcelManagement excelManagement3 = new RecipePlanningExcelManagement();
            List<RecipePlanning> list3 = excelManagement3.readTable();
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
            List<Recipe> list1 = excelManagement1.readTable();
            recipeRepository.saveAll(list1);

            RecipePartExcelManagement excelManagement2 = new RecipePartExcelManagement();
            List<RecipePart> list2 = excelManagement2.readTable();
            recipePartRepository.saveAll(list2);

            RecipePlanningExcelManagement excelManagement3 = new RecipePlanningExcelManagement();
            List<RecipePlanning> list3 = excelManagement3.readTable();
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
