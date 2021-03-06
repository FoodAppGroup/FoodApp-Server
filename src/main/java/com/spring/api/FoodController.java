package com.spring.api;

import com.spring.database.FoodRepository;
import com.spring.model.Food;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    /**
     * @param name is the primary key of the food
     * @return http 200 with the food or http 400 when food was not present
     */
    @ApiOperation("Request to get a food by it's name.")
    @RequestMapping(value = "/food",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> getFood(@RequestParam(value = "name", defaultValue = "Apfel") String name) {
        return foodRepository.findById(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * @return http 200 with list of all foods (can be empty)
     */
    @ApiOperation("Request to get a food by it's name.")
    @RequestMapping(value = "/food/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodRepository.findAll());
    }

    /**
     * @param request json with food structure
     * @return http 200 at successful creation or http 400 when failing
     */
    @ApiOperation("Request to add a new food to the database.")
    @RequestMapping(value = "/food/add",
            method = RequestMethod.POST)
    public ResponseEntity<String> addFood(@RequestBody Food request) {
        try {
            int sizeBeforeStatement = foodRepository.findAll().size();

            Food food = new Food();
            food.setName(request.getName());
            food.setCategory(request.getCategory());
            food.setPackageGramm(request.getPackageGramm());
            food.setKCal(request.getKCal());
            food.setCarbohydrates(request.getCarbohydrates());
            food.setProtein(request.getProtein());
            food.setFat(request.getFat());
            food.setUnit(request.getUnit());
            foodRepository.save(food);

            int sizeAfterStatement = foodRepository.findAll().size();
            return ResponseEntity.ok("Food-Table Size: " + sizeBeforeStatement + " -> " + sizeAfterStatement);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
