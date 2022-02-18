/*
 * GameCharacter
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


import java.util.ArrayList;

enum CharacterState { Idle, Running, Sleeping, Walking, Defending, Dead, Eating, Attacking }

// Note - If the character state is Dead, the character should not be able to do anything.
// Invoking a method when the character is dead should do nothing

//public abstract class GameCharacter {
public class GameCharacter {
    //Attributes
    private String characterName; //Cannot be empty
    private int health; //Must be in range [0..100]. If 0 then character state should be dead. If > 0 the character cannot be dead
    private double weightLimit; //Must be > 0
    private double totalWeightOfItems; //Cannot exceed weightLimit
    private ArrayList<Item> characterInventory = new ArrayList<>();

    // GameCharacter inventory
    // NOTE: The GameCharacter's inventory (items, food, weapons, armour) must be stored within the class.
    // You need to select appropriate collection(s) to allow the character to pickup and drop game items.

    private CharacterState characterState;

    // Getters and setters
    // Setters should be modified to deal with invalid arguments. Throw an IllegalArgumentException if
    // the setter is provided with an invalid argument

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(characterName.isBlank()) {
            throw new IllegalArgumentException("Character name is blank");
        }
        this.characterName = characterName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(health < 0 || health > 100) {
            throw new IllegalArgumentException("Health out of range");
        }

        if(health == 0) {
            characterState = CharacterState.Dead;
            //System.out.println("Character is dead");
        }

        this.health = health;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(weightLimit <= 0.0) {
            throw new IllegalArgumentException("Weight limit too Low");
        }
        this.weightLimit = weightLimit;
    }

    public double getTotalWeightOfItems() {
        return totalWeightOfItems;
    }

    public void setTotalWeightOfItems(double totalWeightOfItems) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(totalWeightOfItems > weightLimit || totalWeightOfItems < 0.0) {
            throw new IllegalArgumentException("Item weight limit exceeded");
        }
        this.totalWeightOfItems = totalWeightOfItems;
    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }


    // GameCharacter Constructor
    // If an invalid argument is provided throw an IllegalArgumentException exception
    public GameCharacter(String characterName, int health, double weightLimit, CharacterState characterState) {
        if(characterName.isBlank() || health < 0 || health > 100 || weightLimit <= 0) {
            throw new IllegalArgumentException();
        }

        this.characterName = characterName;
        this.health = health;
        this.weightLimit = weightLimit;
        this.characterState = characterState;

    }


    // Logic for the eat method
    // If the item at inventoryItemIndex is of the class Food, the item is consumed and removed from the inventory.
    // Eating the item should increase the characters health. 1 unit of health = 25 calories.
    // The state of the food modifies the calorie content for only food with positive calorie values, as follows:
    // Fresh 100% of calories, stale 60%, Mouldy 40%, rotten 10%
    // Eg. an apple is 52 calories per 100g. 100g of mouldy apple will give only 20.8 (52*.4) calories.
    // Health should not exceed 100 which is full health for the character.
    // Consuming poisonous food (food with a negative calorie value) should reduce the health accordingly.
    // When the eat method is invoked, the character's status should also change to "Eating"
    // Return true if the item was food and consumed, otherwise false
    public boolean eat(int inventoryFoodIndex) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return false;
        }

        //Calculates and sets health increase and drops food from the inventory (if the item is food)
        if(characterInventory.get(inventoryFoodIndex).getClass().equals(Food.class)){
            setCharacterState(CharacterState.Eating);

            Food food = (Food)(characterInventory.get(inventoryFoodIndex));
            int calories = food.getCalories();
            double weight = food.getItemWeight();

            //calculates calories on account of the food state
            if(calories > 0) {
                if (food.getFoodState() == FoodState.Stale) {
                    calories *= 0.6;
                } else if (food.getFoodState() == FoodState.Mouldy) {
                    calories *= 0.4;
                } else if (food.getFoodState() == FoodState.Rotten) {
                    calories *= 0.1;
                }
            }

            //calculates 1 health level for every 25 calories per 100g of food
            int healthIncrease = (int)(((calories * weight) / 100) / 25);
            int newHealth = getHealth() + healthIncrease;

            //Ensures new health level is within range
            if(newHealth < 0) {
                setHealth(0);
            } else if(newHealth > 100) {
                setHealth(100);
            } else {
                setHealth(newHealth);
            }

            //Removes eaten food item from the inventory
            dropItem(characterInventory.get(inventoryFoodIndex));
            return true;
        }
        return false;
    }

    // The attack method should  return a string with the following information:
    // "<this.getName()> is attacking targetCharacter.getName() with a <equippedWeapon.getItemName()>
    // Example output “Lord Percival is attacking Lord Blackadder with a sword"
    // If the attacker is using two weapons:
    // Example output “Lord Percival is attacking Lord Blackadder with a sword and a knife"
    public String attack(GameCharacter targetCharacter) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return null;
        }

        characterState = CharacterState.Attacking;

        String returnString = this.getCharacterName()
                + " is attacking " + targetCharacter.getCharacterName()
                + " with a" + equippedWeaponName();

        return returnString;
    }

    // The equipWeapon will equip the weapon at inventoryWeaponIndex
    // If the item in the inventory index is a weapon the method should return true, otherwise false
    // If the weapon requires both hands (doubleHanded = true) the character must unequip any weapons or shields
    // they are currently holding.
    public boolean equipWeapon(int inventoryWeaponIndex){
        if (isDead()) {
            System.out.println("The Character is Dead");
            return false;
        }

        if(characterInventory.get(inventoryWeaponIndex).getClass().equals(Weapon.class)) {
            Weapon weapon = (Weapon) (characterInventory.get(inventoryWeaponIndex));
            weapon.setWeaponEquipped(true);
            return true;
        }
        return false;
    }

    // If the item in the inventory at inventoryWeaponIndex is a weapon, unequip it and return true, otherwise false
    // Weapons that are unequipped remain in the inventory.
    public boolean unEquipWeapon(int inventoryWeaponIndex){
        if (isDead()) {
            System.out.println("The Character is Dead");
            return false;
        }

        if(characterInventory.get(inventoryWeaponIndex).getClass().equals(Weapon.class)) {
            Weapon weapon = (Weapon) (characterInventory.get(inventoryWeaponIndex));
            weapon.setWeaponEquipped(false);
            return true;
        }
        return false;
    }

    // The equipArmour method should equip the item of armour at armourIndex in the inventory
    // The character can only equip one shield at a time. If a shield is already equipped and the armourIndex
    // argument points to another shield, then the shield at armourIndex is equipped instead. If the armourIndex points
    // to a wearable piece of armour the GameCharacter should put it on. The character may wear multiple pieces of armour
    public boolean equipArmour(int armourIndex) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return false;
        }

        if(characterInventory.get(armourIndex).getClass().equals(Armour.class)) {
            Armour armour = (Armour) (characterInventory.get(armourIndex));

            //If the armour type is 'Hold', check if there is already another hold armour equipped
            if(armour.getArmourType() == ArmourTypes.Hold) {
                isShieldEquipped();
            }

            armour.setArmourEquipped(true);
            return true;
        }
        return false;
    }

    // The method should remove the item of armour at armourIndex from the gameCharacter. The character essentially
    // takes off the piece of armour but it remains in their inventory
    public void removeArmour(int armourIndex){
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(characterInventory.get(armourIndex).getClass().equals(Armour.class)) {
            Armour armour = (Armour) (characterInventory.get(armourIndex));
            armour.setArmourEquipped(false);
        }
    }

    // The defend method should set the characters state to defending
    public void defend(){
        characterState = CharacterState.Defending;
    }

    // The method should change the characters state to walking
    public void walk() {
        characterState = CharacterState.Walking;
    }

    // The method should change the characters state to running
    public void run(){
        characterState = CharacterState.Running;
    }

    // The method should change the characters state to sleeping
    // The character's health should increase by 20 but should not exceed the maximum (100)
    public void sleep(){
        characterState = CharacterState.Sleeping;

        int newHealth = getHealth() + 20;

        //ensures new health level does not pass 100
        if(newHealth > 100) {
            setHealth(100);
        } else {
            setHealth(newHealth);
        }
    }

    // If the character is sleeping the wakeUp method should change the character's state to Idle
    // If the character is not sleeping, the method should not change the character's state.
    public void wakeUp(){
        if(characterState == CharacterState.Sleeping) {
            characterState = CharacterState.Idle;
        }
    }

    // The pickUpItem method should add the item to the game characters inventory if the item does not exceed the
    // current weightLimit for the character. If the character does pick up the item the weight of the item
    // should be added to the total weight of items carried by the character
    // The item object may be an instance of the Item or any subclass (armour, weapon or food)
    public void pickUpItem(Item item){
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        if(item.getItemWeight() + getTotalWeightOfItems() <= weightLimit) {
            addToInventory(item);
            setTotalWeightOfItems(getTotalWeightOfItems() + item.getItemWeight());
        } else {
            System.out.println("Item Exceeds Weight Limit");
        }
    }

    // The dropItem method should remove the item from the game character's inventory. The total weight of items
    // should be updated accordingly.
    // The item object may be an instance of the Item or any subclass (armour, weapon or food)
    public void dropItem(Item item){
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        removeInventoryItem(item);
        setTotalWeightOfItems(getTotalWeightOfItems() - item.getItemWeight());
    }

    // The defence value is determined by totalling the armourDefence values of all armour items currently equipped
    // by the game character
    public double getCharacterDefence() {
        double characterDefence = 0.0;

        //Iterates through the inventory checking for armour class items, summing their defence values if equipped
        for (Item item : characterInventory) {
            if (item.getClass().equals(Armour.class)) {
                Armour armour = (Armour)item;
                if(armour.isArmourEquipped()) {
                    characterDefence += armour.getArmourDefence();
                }
            }
        }
        return characterDefence;
    }

    // The attack value is determined by totalling the weaponHitStrength values of all weapons currently equipped
    // by the game character
    public int getCharacterAttackValue() {
        int characterAttack = 0;

        //Iterates through the inventory summing attack values if the item is a weapon and equipped
        for (Item item : characterInventory) {
            if (item.getClass().equals(Weapon.class)) {
                Weapon weapon = (Weapon)item;
                if(weapon.isWeaponEquipped()) {
                    characterAttack += weapon.getWeaponHitStrength();
                }
            }
        }
        return characterAttack;
    }

    // Note - You can implement additional supporting private methods if you want. Add them below this section
    // Additional methods ------------------------------------------------------------------------------------=

    private void addToInventory(Item item) {
        if (isDead()) {
            System.out.println("The Character is Dead");
            return;
        }

        characterInventory.add(item);
    }

    public boolean getInventoryItem(Item item) {
        return characterInventory.contains(item);
    }

    public int getItemInventoryIndex(Item item) {
            return characterInventory.indexOf(item);
    }

    private void removeInventoryItem(Item item) {
        characterInventory.remove(item);
    }

    public int getInventorySize() {
        return characterInventory.size();
    }

    //checks if the character is dead
    private boolean isDead() {
        if(characterState == CharacterState.Dead) {
            return true;
        }
        return false;
    }

    //Returns a string containing the equipped weapon names
    private String equippedWeaponName() {
        String equippedWeapons = " ";
        boolean secondWeapon = false;

        //cycles the inventory in search of weapons that are equipped to store the weapon names in a String
        for (Item item : characterInventory) {
            if (item.getClass().equals(Weapon.class)) {
                Weapon weapon = (Weapon)item;
                if(weapon.isWeaponEquipped()) {
                    if(secondWeapon) {
                        equippedWeapons += " and a ";
                    }
                    equippedWeapons += weapon.getItemName();
                    secondWeapon = true;
                }
            }
        }
        return equippedWeapons;
    }

    //Checks if another hold type armour is equipped
    private void isShieldEquipped() {
        for (Item item : characterInventory) {
            if (item.getClass().equals(Armour.class)) {
                Armour armour = (Armour)item;
                if(armour.getArmourType() == ArmourTypes.Hold) {
                    armour.setArmourEquipped(false);
                }
            }
        }
    }

    @Override
    public String toString() {
        return getCharacterName() + " has a health level of "
                + getHealth() + ", a weight limit of "
                + getWeightLimit() + ", a defence level of "
                + getCharacterDefence() + ", an attack value of "
                + getCharacterAttackValue() + ", is carrying "
                + getTotalWeightOfItems() + ", and is currently "
                + getCharacterState() + ". \n";
    }
}
