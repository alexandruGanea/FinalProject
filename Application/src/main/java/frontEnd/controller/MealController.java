package frontEnd.controller;

import business.dto.MealDTO;
import business.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "meals")
public class MealController {

    @Autowired
    MealService mealService;

    @PostMapping(path = "/insertMeal")
    public ResponseEntity insertMeal (@Valid @RequestBody MealDTO mealDTO){
        if(mealService.isInserted(mealDTO)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Meal for hotel " + mealDTO.getHotelDTO().getName() + " is already present");
        }else{
            mealService.insertMeal(mealDTO);
            return ResponseEntity.ok("Meal for hotel " + mealDTO.getHotelDTO().getName() + " was added.");
        }
    }
}
