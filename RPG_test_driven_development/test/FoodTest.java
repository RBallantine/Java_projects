/*
 * FoodTest
 *
 * Version information
 *
 * Date 30/01/2021
 *
 * Author: Ronan Ballantine L00162110
 *
 * Copyright notice
 */

package com.lyit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FoodTest {

    //Provide different food states
    @DataProvider(name = "SetFoodStateDP")
    public Object[][] SetFoodStateDP() {
        return new Object[][]{{FoodState.Fresh}, {FoodState.Stale}, {FoodState.Mouldy}, {FoodState.Rotten}};
    }

    //Tests if set foodState sets the foodstate correctly
    @Test (dataProvider = "SetFoodStateDP")
    public void SetFoodState_CorrectArgumentProvided_FoodStateSetsCorrectly(FoodState foodState) {
        Food testFood = new Food("SuperBerry", 80, 99, 99, FoodState.Mouldy, 1000);
        testFood.setFoodState(foodState);
        Assert.assertEquals(testFood.foodState, foodState);

        //Gives Printed Visual of Test
        //System.out.println("testfood foodstate = " + testFood.foodState + ", foodstate provided = " + foodState);
    }

    //Provides integer values outside the range[-1000 to 1000]
    @DataProvider(name = "SetInvalidCaloriesDP")
    public Object[][] SetInvalidCaloriesDP() {
        return new Object[][]{{1001}, {2000}, {-1001}, {-2000}};
    }

    //Tests if an exception is thrown if the calories are set out of bounds
    @Test (dataProvider = "SetInvalidCaloriesDP", expectedExceptions = IllegalArgumentException.class)
    public void SetCalories_IncorrectArgumentProvided_IllegalArgumentExceptionThrown(int calories) {

        Food testFood = new Food("SuperBerry", 80, 99, 99, FoodState.Fresh, 1000);
        testFood.setCalories(calories);
    }

    //Provides valid calorie integers
    @DataProvider(name = "SetValidCaloriesDP")
    public Object[][] SetValidCaloriesDP() {
        return new Object[][]{{1000}, {200}, {-1000}, {-500}, {0}};
    }

    //Tests if the calories are set correctly
    @Test (dataProvider = "SetValidCaloriesDP")
    public void SetCalories_CorrectArgumentProvided_CaloriesSetCorrectly(int calories) {

        Food testFood = new Food("SuperBerry", 80, 99, 99, FoodState.Fresh, 1000);
        testFood.setCalories(calories);
        int actualResult = testFood.getCalories();

        Assert.assertEquals(actualResult, calories);
    }

    //Provides double values for food weight and integer values for calories (one parameter is invalid per test)
    @DataProvider(name = "FoodConstructorDP")
    public Object[][] FoodConstructorDP() {
        return new Object[][] {{0.0, 56}, {-1.0, 56}, {100.0, 2000}, {100.0, -1001}};
    }

    //Tests if an illegal argument is thrown when passing an incorrect argument to construct the food item
    @Test (dataProvider = "FoodConstructorDP", expectedExceptions = IllegalArgumentException.class)
    public void FoodConstructor_IncorrectParameterProvided_IllegalArgumentExceptionThrown(double foodWeight, int calories) {
        Food testFood = new Food("Apple", foodWeight, 2.0, 5, FoodState.Fresh, calories);
    }
}