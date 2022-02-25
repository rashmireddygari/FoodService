package com.learning.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.learning.enums.FoodType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "food_tbl")
public class FoodItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodId;
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
