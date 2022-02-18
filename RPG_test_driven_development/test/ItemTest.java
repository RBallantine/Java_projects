/*
 * ItemTest
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

public class ItemTest {

    //Provides item constructor with one illegal argument per test
    @DataProvider(name = "ItemConstructorDP")
    public Object[][] ItemConstructorDP(){
        return new Object[][] {{"", 1.0, 1.0, 1},
                {"Max", 0.0, 1.0, 1},
                {"Max", -1.0, 1.0, 1},
                {"Max", 1.0, -0.1, 1},
                {"Max", 1.0, 1.0, -1}};
    }

    //Tests if an exception is thrown when constructing an item with an illegal argument
    @Test (dataProvider = "ItemConstructorDP", expectedExceptions = IllegalArgumentException.class)
    public void ItemConstructor_IncorrectValuesProvided_ThrowsIllegalArgumentException(String itemName, double weight, double value, int magic) {

        Item testItem = new Item(itemName, weight, value, magic);
    }

    //Provides blank strings
    @DataProvider(name = "ItemSetInvalidNameDP")
    public Object[][] ItemSetInvalidNameDP(){
        return new Object[][] {{""}, {" "}};
    }

    //Tests if an exception is thrown when setting the item name to a blank string
    @Test (dataProvider = "ItemSetInvalidNameDP", expectedExceptions = IllegalArgumentException.class)
    public void setItemName_EmptyNameProvided_ThrowsIllegalArgumentException(String itemName) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemName(itemName);
    }

    //Provides valid name Strings
    @DataProvider(name = "ItemSetValidNameDP")
    public Object[][] ItemSetValidNameDP(){
        return new Object[][] {{"Superman"}, {"Lex Luthor"}};
    }

    //Tests if Name is set correctly
    @Test (dataProvider = "ItemSetValidNameDP")
    public void setItemName_ValidNameProvided_NameSetCorrectly(String itemName) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemName(itemName);
        String actualResult = testItem.getItemName();

        Assert.assertEquals(actualResult, itemName);
    }

    //Provides double values below or equal to 0.0
    @DataProvider(name = "ItemSetInvalidWeightDP")
    public Object[][] ItemSetInvalidWeightDP(){
        return new Object[][] {{0.0},{-0.00001}, {-1.0}, {-100}};
    }

    //Tests if an exception is thrown when setting the item weight not greater than 0.0
    @Test (dataProvider = "ItemSetInvalidWeightDP", expectedExceptions = IllegalArgumentException.class)
    public void SetItemWeight_IncorrectValueProvided_ThrowsIllegalArgumentException(double weight) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemWeight(weight);
    }

    //Provides double values above 0.0
    @DataProvider(name = "ItemSetValidWeightDP")
    public Object[][] ItemSetValidWeightDP(){
        return new Object[][] {{0.1},{0.00001}, {1.0}, {100}};
    }

    //Tests if item weight is set correctly
    @Test (dataProvider = "ItemSetInvalidWeightDP", expectedExceptions = IllegalArgumentException.class)
    public void SetItemWeight_CorrectValueProvided_ItemWeightSetCorrectly(double weight) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemWeight(weight);
        double actualResult = testItem.getItemWeight();

        Assert.assertEquals(actualResult, weight);
    }

    //Provides double values less then 0.0
    @DataProvider(name = "ItemSetValueDP")
    public Object[][] ItemSetValueDP(){
        return new Object[][] {{-0.0001}, {-1.0}};
    }

    //Tests if an exception is thrown when setting the item value less than 0.0
    @Test (dataProvider = "ItemSetValueDP", expectedExceptions = IllegalArgumentException.class)
    public void SetItemValue_IncorrectValueProvided_ThrowsIllegalArgumentException(double value) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemValue(value);
    }

    //Provides integer values less than 0
    @DataProvider(name = "ItemSetMagicDP")
    public Object[][] ItemSetMagicDP(){
        return new Object[][] {{-1}, {-100}, {-1000}};
    }

    //Tests if an exception is thrown when setting the item magic below 0
    @Test (dataProvider = "ItemSetMagicDP", expectedExceptions = IllegalArgumentException.class)
    public void SetItemMagicValue_IncorrectValueProvided_ThrowsIllegalArgumentException(int magic) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemMagicValue(magic);
    }
}