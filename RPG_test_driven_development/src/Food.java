/*
 * Food
 *
 * Version information
 *
 * Date 30/01/2021
 *
 * Author: <Ronan Ballantine L00162110
 *
 * Copyright notice
 */

package com.lyit;

enum FoodState {Fresh, Stale, Mouldy, Rotten};

public class Food extends Item {

    // Attributes
    FoodState foodState;
    int calories; // Calories per 100grams. Must be in the range [-1000 to 1000].
    // Food with a negative value is poisonous and will reduce the characters health if eaten

    /* Exemplar calories of sample food per 100g
    * Apple 52 calories
    * Steak 271 calories
    * Mushroom 22 calories
    * Bread 264 calories */

    // Getters and Setters
    // Throw an IllegalArgumentException if a Setter is provided with an invalid argument

    public FoodState getFoodState() {
        return foodState;
    }

    public void setFoodState(FoodState foodState) {
        this.foodState = foodState;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        if(calories < -1000 || calories > 1000) {
            throw new IllegalArgumentException("Invalid Calories Argument");
        }
        this.calories = calories;
    }


    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Food(String itemName, double itemWeight, double itemValue, int itemMagic, FoodState foodState, int calories) {
        super(itemName, itemWeight, itemValue, itemMagic);

        if(calories < -1000 || calories > 1000) {
            throw new IllegalArgumentException("Invalid Calories Argument");
        }

        this.foodState = foodState;
        this.calories = calories;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + ". It has a food state of "
                + getFoodState() + ", and a calorie count of "
                + getCalories() + ".\n";
    }
}
