package com.learning.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.FoodItem;
import com.learning.enums.FoodType;
import com.learning.exception.NoDataFoundException;
import com.learning.repo.FoodItemRepo;

@RestController
@RequestMapping("/api/food")
@Validated

public class FoodController {

	@Autowired
	FoodItemRepo foodItemRepo;
	@PostMapping(value = "/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createFood(@Valid @RequestBody FoodItem foodItem){
		FoodItem foodItem2 = foodItemRepo.save(foodItem);		
		return ResponseEntity.status(201).body(foodItem2);
	}
	
	@GetMapping("/{foodId}")
	public ResponseEntity<?> getFoodById(@PathVariable("foodId") @Min(1) Long foodId) {
		FoodItem foodItem = foodItemRepo.findById(foodId).orElseThrow(()-> new NoDataFoundException("Food Item Not Found"));
		return ResponseEntity.ok(foodItem);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllFood() {
		List<FoodItem> foodItems = foodItemRepo.findAll();
		if(foodItems.size()<1) {
			return ResponseEntity.status(200).body("[]");
		}
		return ResponseEntity.ok(foodItems);
	}

	@DeleteMapping("/{foodId}")
	public ResponseEntity<?> deleteFood(@PathVariable Long foodId) {
		foodItemRepo.deleteById(foodId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "success");
		map.put("data", Long.toUnsignedString(foodId));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
	}
	
	@PutMapping("/{foodId}")
	public ResponseEntity<?> updateFood(@Valid @RequestBody FoodItem foodItem, @PathVariable Long foodId){
		
		return null;
	}
	
	@GetMapping("/all/{foodType}")
	public ResponseEntity<?> getFoodByFoodType(@PathVariable String foodType){
		List<FoodItem> list = foodItemRepo.findByFoodType(FoodType.valueOf(foodType));
		return ResponseEntity.ok(list);
	}
	@GetMapping("/all/asc/{foodType}")
	public ResponseEntity<?> getFoodByFoodTypeAsc(@PathVariable String foodType){
		List<FoodItem> list = foodItemRepo.findByFoodType(FoodType.valueOf(foodType));
		Collections.sort(list,(a,b)-> a.getFoodId().compareTo(b.getFoodId()));
		return ResponseEntity.ok(list);
	}
	@GetMapping("/all/desc/{foodType}")
	public ResponseEntity<?> getFoodByFoodTypeDesc(@PathVariable String foodType){
		List<FoodItem> list = foodItemRepo.findByFoodType(FoodType.valueOf(foodType));
		Collections.sort(list,(a,b)-> b.getFoodId().compareTo(a.getFoodId()));
		return ResponseEntity.ok(list);
	}
}
