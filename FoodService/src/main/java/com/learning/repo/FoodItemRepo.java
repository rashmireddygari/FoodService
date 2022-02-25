package com.learning.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.FoodItem;
import com.learning.enums.FoodType;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Long>{
	public List<FoodItem> findByFoodType(FoodType foodType);
}
