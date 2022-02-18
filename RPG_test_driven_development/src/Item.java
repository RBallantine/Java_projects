/*
 * Item
 *
 * Version information
 *
 * Date 30/01/2021
 *
 * Author: Ronan Ballantine  L00162110
 *
 * Copyright notice
 */

package com.lyit;

public class Item {
    private String itemName;  //Cannot be empty
    private double itemWeight; //Must be > 0
    private double itemValue; //Must >=0
    private int itemMagicValue; //Must >=0

    // Getters and Setters
    // Throw an IllegalArgumentException if a Setter is provided with an invalid argument
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        if(itemName.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.itemName = itemName;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        if(itemWeight <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.itemWeight = itemWeight;
    }

    public double getItemValue() {
        return itemValue;
    }

    public void setItemValue(double itemValue) {
        if(itemValue < 0.0) {
            throw new IllegalArgumentException();
        }
        this.itemValue = itemValue;
    }

    public int getItemMagicValue() {
        return itemMagicValue;
    }

    public void setItemMagicValue(int itemMagicValue) {
        if(itemMagicValue < 0) {
            throw new IllegalArgumentException();
        }
        this.itemMagicValue = itemMagicValue;
    }

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Item(String itemName, double itemWeight, double itemValue, int itemMagic) {
        if(itemName.isBlank() || itemWeight <= 0.0 || itemValue <0.0 || itemMagic < 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.itemName = itemName;
        this.itemWeight = itemWeight;
        this.itemValue = itemValue;
        this.itemMagicValue = itemMagic;

    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return getItemName() + " has a weight of "
                + getItemWeight() + "g, has a value of "
                + getItemValue() + " and a magic value of "
                + getItemMagicValue();
    }
}
