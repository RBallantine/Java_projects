/*
 * Armour
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

enum ArmourTypes { Wearable, Hold }; //Armour can be wearable (eg helmet) or holdable (eg shield)
enum ArmourMaterial { Cardboard, Leather, Wood, Iron, Steel };

public class Armour extends Item {
    //Armour class Fields
    private int armourDefence; //The defensive value of the armour. Must be in range [1..100]
    private int armourHealth; //The health of the armour. Must be in range [1..100]
    private  boolean equipped; //A simple boolean flag. True means the armour is being held (shield) or worn (eg gloves)
    private ArmourTypes armourType;
    private ArmourMaterial armourMaterial;

    // Getters and setter
    // Throw an IllegalArgumentException if a Setter is provided with an invalid argument
    public int getArmourDefence() {
        return armourDefence;
    }

    public void setArmourDefence(int armourDefence) {
        if(armourDefence < 1 || armourDefence > 100) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.armourDefence = armourDefence;
    }

    public int getArmourHealth() {
        return armourHealth;
    }

    public void setArmourHealth(int armourHealth) {
        if(armourHealth < 1 || armourHealth > 100) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.armourHealth = armourHealth;
    }

    public ArmourTypes getArmourType() {
        return armourType;
    }

    //Method possibly redundant? If armour type cant be changed after creation
    public void setArmourType_(ArmourTypes armourType) {
        if(this.armourType != armourType) {
            return;
        }

        this.armourType = armourType;
    } //Armour type should not change once created

    public ArmourMaterial getArmourMaterial() {
        return armourMaterial;
    }

    public void setArmourMaterial(ArmourMaterial armourMaterial) {
        if(this.armourMaterial != armourMaterial) {
            return;
        }

        this.armourMaterial = armourMaterial;
    } //Armour material should not change once created

    // Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception

    public Armour(String itemName, double itemWeight, double itemValue, int itemMagic, int armourDefence, int armourHealth, boolean equipped, ArmourTypes armourType, ArmourMaterial armourMaterial) {
        super(itemName, itemWeight, itemValue, itemMagic);

        if(armourDefence < 1 || armourDefence > 100 || armourHealth < 1 || armourHealth > 100) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        this.armourDefence = armourDefence;
        this.armourHealth = armourHealth;
        this.equipped = equipped;
        this.armourType = armourType;
        this.armourMaterial = armourMaterial;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------
    public void setArmourEquipped(boolean armourEquipped) {
        equipped = armourEquipped;
    }

    public boolean isArmourEquipped() {
        return equipped;
    }


    @Override
    public String toString() {
        return super.toString() + ". It has an armour defence of "
                + getArmourDefence() + ", an armour health of "
                + getArmourHealth() + ".\n Armour is equipped: "
                + isArmourEquipped() + ", is of type "
                + getArmourType() + " and is made of "
                + getArmourMaterial() + "\n";
    }
}
