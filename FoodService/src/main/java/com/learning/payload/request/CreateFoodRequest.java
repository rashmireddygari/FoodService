package com.learning.payload.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodRequest {
	@NotBlank
	private String foodName;
	@DecimalMin("0.01")
	private double foodCost;
	@NotNull
	@Enumerated(EnumType.STRING)
	private FoodType foodType;
	@NotBlank
	private String description;
}
