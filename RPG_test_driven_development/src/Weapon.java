/*
 * Weapon
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

public class Weapon extends  Item {

    // Class attributes
    private int weaponHitStrength;//The damage the weapon does. Must be in range [1..100]
    private int weaponHealth; //The health of the weapon. Must be in range [1..100]
    private boolean doubleHanded; // Does the weapon require both hands? True or false.
    private boolean weaponEquipped; // Is the weapon being held, true or false.

    // Getters and Setters
    // Throw an IllegalArgumentException if a setter is provided with an invalid argument
    public int getWeaponHitStrength() {
        return weaponHitStrength;
    }

    //The damage the weapon does. Must be in range [1..100]
    public void setWeaponHitStrength(int weaponHitStrength) {
        if(weaponHitStrength < 1 || weaponHitStrength > 100) {
            throw new IllegalArgumentException("Invalid weapon strength");
        }
        this.weaponHitStrength = weaponHitStrength;
    }

    public int getWeaponHealth() {
        return weaponHealth;
    }

    //The health of the weapon. Must be in range [1..100]
    public void setWeaponHealth(int weaponHealth) {
        if(weaponHealth < 1 || weaponHealth > 100) {
            throw new IllegalArgumentException("Invalid weapon health");
        }
        this.weaponHealth = weaponHealth;
    }

    public boolean isDoubleHanded() {
        return doubleHanded;
    }

    public void setDoubleHanded(boolean doubleHanded) {
        this.doubleHanded = doubleHanded;
    }

    public boolean isWeaponEquipped() {
        return weaponEquipped;
    }

    public void setWeaponEquipped(boolean weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public Weapon(String itemName, double itemWeight, double itemValue, int itemMagic, int weaponHitStrength, int weaponHealth, boolean doubleHanded) {
        super(itemName, itemWeight, itemValue, itemMagic);

        if(weaponHealth < 1 || weaponHealth > 100) {
            throw new IllegalArgumentException("Invalid weapon health");
        }

        if(weaponHitStrength < 1 || weaponHitStrength > 100) {
            throw new IllegalArgumentException("Invalid weapon strength");
        }

        this.weaponHitStrength = weaponHitStrength;
        this.weaponHealth = weaponHealth;
        this.doubleHanded = doubleHanded;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + ". It has a weapon hit strength of "
                        + getWeaponHitStrength() + ", a weapon health of "
                        + getWeaponHealth() + ".\n The weapon is double handed: "
                        + isDoubleHanded() + ", and is equipped: "
                        + isWeaponEquipped() + "\n";
    }
}
