/*
 * Main
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

public class Main {

    public static void main(String[] args) {
        //initialise characters
        GameCharacter luke = new GameCharacter("Luke", 50, 100, CharacterState.Idle);
        GameCharacter bubba = new GameCharacter("Bubba Fet", 80, 10000,
                CharacterState.Idle);

        //Re-initialise Character
        luke.setCharacterName("Luke Skywalker");
        luke.setHealth(80);
        luke.setWeightLimit(10000);
        luke.setCharacterState(CharacterState.Running);

        System.out.println(luke);
        System.out.println(bubba);

        //initialise weapons
        Weapon lightSaber = new Weapon("light", 2.0, 6.0, 5, 1,
                1, true);
        Weapon blaster = new Weapon("Blaster", 500.0, 100.0, 30, 80,
                100, false);

        //Re-initialise Weapon
        lightSaber.setItemName("Light Saber");
        lightSaber.setItemWeight(250.0);
        lightSaber.setItemValue(800.0);
        lightSaber.setItemMagicValue(50);
        lightSaber.setWeaponHitStrength(90);
        lightSaber.setWeaponHealth(80);
        lightSaber.setDoubleHanded(false);

        System.out.println(lightSaber);
        System.out.println(blaster);

        //initialise Food
        Food broth = new Food("bro", 10, .5, 5, FoodState.Stale, 5);
        Food poison = new Food("Poison Drink", 300, 600, 90, FoodState.Fresh,
                -1000);

        //Re-initialise Food
        broth.setItemName("Broth");
        broth.setItemWeight(100.0);
        broth.setItemValue(5.0);
        broth.setItemMagicValue(1);
        broth.setFoodState(FoodState.Fresh);
        broth.setCalories(500);

        System.out.println(broth);
        System.out.println(poison);

        //initialise Armour
        Armour smallShield = new Armour("shield", 25.0, 5.0, 1,
                1, 1, false, ArmourTypes.Wearable, ArmourMaterial.Wood);
        Armour bigShield = new Armour("Big Shield", 350.0, 70.0, 12,
                80, 95, false, ArmourTypes.Hold, ArmourMaterial.Steel);
        Armour breastPlate = new Armour("Breast Plate", 400.0, 120.0, 9,
                90, 1, false, ArmourTypes.Wearable, ArmourMaterial.Iron);
        Armour helmet = new Armour("Helmet", 200.0, 50.0, 6, 60,
                100, false, ArmourTypes.Wearable, ArmourMaterial.Cardboard);

        //Re-initialise Armour
        smallShield.setItemName("Small Shield");
        smallShield.setItemWeight(250.0);
        smallShield.setItemValue(50.0);
        smallShield.setItemMagicValue(10);
        smallShield.setArmourDefence(70);
        smallShield.setArmourHealth(10);
        smallShield.setArmourEquipped(true);
        smallShield.setArmourType_(ArmourTypes.Hold);
        smallShield.setArmourMaterial(ArmourMaterial.Leather);

        System.out.println(smallShield);
        System.out.println(bigShield);
        System.out.println(breastPlate);
        System.out.println(helmet);

        //Initialise Items
        Item cruiserKeys = new Item("Cruiser Keys", 100.0, 20.0, 40);
        Item credits = new Item("Credits", 10.0, 200.0, 1);

        System.out.println(credits);
        System.out.println(cruiserKeys);
        System.out.println("***************************************************************************************\n");

        //add items to inventory
        luke.pickUpItem(lightSaber);
        luke.pickUpItem(cruiserKeys);
        luke.pickUpItem(broth);

        //equip light saber
        luke.equipWeapon(luke.getItemInventoryIndex(lightSaber));

        //eat non food
        luke.eat(0);

        System.out.println(luke);
        //eat broth (health increases by 20)
        luke.eat(luke.getItemInventoryIndex(broth));
        System.out.println("After eating " + broth.getItemName() + ":\n" + luke);

        bubba.sleep();
        System.out.println(bubba);
        bubba.wakeUp();

        //Add items to inventory
        bubba.pickUpItem(blaster);
        bubba.pickUpItem(smallShield);
        bubba.pickUpItem(bigShield);
        bubba.pickUpItem(helmet);
        bubba.pickUpItem(breastPlate);
        bubba.pickUpItem(credits);
        bubba.pickUpItem(poison);

        //Equip Weapons and armour
        bubba.equipWeapon(bubba.getItemInventoryIndex(blaster));
        bubba.equipArmour(bubba.getItemInventoryIndex(bigShield));
        bubba.equipArmour(bubba.getItemInventoryIndex(helmet));
        bubba.equipArmour(bubba.getItemInventoryIndex(helmet));

        System.out.println("After sleeping: \n" + bubba);

        //Luke and Bubba Battle
        luke.attack(bubba);
        bubba.defend();
        bubba.attack(luke);
        luke.defend();

        bubba.eat(bubba.getItemInventoryIndex(poison));
        System.out.println("After drinking poison \n" + bubba);

        //character is dead
        bubba.attack(luke);

    }
}
