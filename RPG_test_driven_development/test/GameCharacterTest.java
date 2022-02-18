/*
 * GameCharacterTest
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
import java.lang.reflect.InvocationTargetException;

public class GameCharacterTest {

    //Data provider provides and empty or blank String.
    @DataProvider(name = "SetCharacterNameDP")
    public Object[][] SetCharacterNameDP() {
        return new Object[][]{{""}, {" "}};
    }

    //Tests if an Illegal argument exception is thrown when attempting to set the character's name with a blank String
    @Test (dataProvider = "SetCharacterNameDP", expectedExceptions = IllegalArgumentException.class)
    public void SetCharacterName_InvalidArgumentProvided_IllegalArgumentExceptionThrown(String characterName){

        GameCharacter testCharacter = new GameCharacter("Goku", 99, 99.0, CharacterState.Attacking);
        testCharacter.setCharacterName(characterName);
    }

    //Provides valid name Strings
    @DataProvider(name = "SetValidNameDP")
    public Object[][] SetValidNameDP(){
        return new Object[][] {{"Spiderman"}, {"Doc Oc"}};
    }

    //Tests if Name is set correctly
    @Test (dataProvider = "SetValidNameDP")
    public void setItemName_ValidNameProvided_NameSetCorrectly(String itemName) {

        Item testItem = new Item("Max", 1, 1, 1);
        testItem.setItemName(itemName);
        String actualResult = testItem.getItemName();

        Assert.assertEquals(actualResult, itemName);
    }

    //Provides integers outside the range of [0-100]
    @DataProvider(name = "SetHealthOutOfBoundsDP")
    public Object[][] SetHealthOutOfBoundsDP() {
        return new Object[][] {{-1}, {-100}, {101}, {200}};
    }

    //Tests if an Illegal argument exception is thrown when attempting to set the character's health outside the range of [0-100]
    @Test (dataProvider = "SetHealthOutOfBoundsDP", expectedExceptions = IllegalArgumentException.class)
    public void SetHealth_InvalidArgumentsProvided_ThrowsIllegalArgumentException(int health) {

        GameCharacter testCharacter = new GameCharacter("Gohan", 99, 99.0, CharacterState.Attacking);
        testCharacter.setHealth(health);
    }

    //Provides double values less than or equal to 0.0
    @DataProvider(name = "SetWeightLimitDP")
    public Object[][] SetWeightLimitDP() {
        return new Object[][] {{0.0}, {-0.001}, {-100.0}};
    }

    //Tests if an Illegal argument exception is thrown when attempting to set the character's weight less than or equal to 0.0
    @Test (dataProvider = "SetWeightLimitDP", expectedExceptions = IllegalArgumentException.class)
    public void SetWeightLimit_InvalidArgumentsProvided_ThrowsIllegalArgumentException(double weightLimit) {
        GameCharacter testCharacter = new GameCharacter("Mr. Robot", 99, 99.0, CharacterState.Attacking);
        testCharacter.setWeightLimit(weightLimit);
    }

    //provides double values above the character's weight limit and below 0
    @DataProvider(name = "SetTotalWeightOfItemsDP")
    public Object[][] SetTotalWeightOfItemsDP() {
        return new Object[][] {{45.0001}, {100.0}, {46.0}, {-0.1}, {-1.0}};
    }

    //Tests if an Illegal argument exception is thrown when attempting
    //to set the character's total weight of items above it's weight limit or below 0
    @Test (dataProvider = "SetTotalWeightOfItemsDP", expectedExceptions = IllegalArgumentException.class)
    public void SetTotalWeightOfItems_WeightAboveLimit_ExceptionThrown(double weightOfItems) {
        GameCharacter testCharacter = new GameCharacter("Mr. Robot", 99, 45.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(weightOfItems);
    }

    //Provides different character constructors with 1 invalid argument per test
    @DataProvider(name = "ConstructorDP")
    public Object[][] ConstructorDP() {
        //character name[blank string], health[Range 0-100], weight limit[>0]
        return new Object[][] {{"", 1, 1.0, CharacterState.Attacking},
                {" ", 1, 1.0, CharacterState.Dead},
                {"Mikey", 101, 1.0, CharacterState.Defending},
                {"Leo", -1, 1.0, CharacterState.Eating},
                {"Donny", 1, -0.1, CharacterState.Idle},
                {"Raph", 1, 0.0, CharacterState.Sleeping}};
    }

    //Tests if an Illegal argument exception is thrown when attempting to construct a character with an invalid argument
    @Test (dataProvider = "ConstructorDP", expectedExceptions = IllegalArgumentException.class)
    public void Constructor_InvalidArgumentProvided_IllegalArgumentExceptionThrown(String characterName, int health,
                                                                                   double weightLimit, CharacterState characterState) {
        GameCharacter testCharacter = new GameCharacter(characterName, health, weightLimit, characterState);
    }

    //Provides Valid food items
    @DataProvider(name = "EatInventoryItemIsFoodDP")
    public Object[][] EatInventoryItemIsFoodDP() {
        Food testFood1 = new Food("Apple", 100.0, 5.0, 5, FoodState.Fresh, 56);
        Food testFood2 = new Food("Pear", 100.0, 5.0, 5, FoodState.Fresh, 60);
        return new Object[][] {{testFood1}, {testFood2}};
    }

    //Tests if a food item is removed from the inventory when the eat method is called.
    @Test (dataProvider = "EatInventoryItemIsFoodDP")
    public void Eat_InventoryItemIsFood_ItemRemovedFromInventory(Item item) {
        GameCharacter testCharacter = new GameCharacter("Max", 70, 800, CharacterState.Eating);
        testCharacter.pickUpItem(item);
        System.out.println("Inventory size before = " + testCharacter.getInventorySize());
        testCharacter.eat(0);
        System.out.println("Inventory size after = " + testCharacter.getInventorySize());

        int expectedResult = 0;
        int actualResult = testCharacter.getInventorySize();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if a food item not in element 0 is removed from the inventory when the eat method is called
    @Test (dataProvider = "EatInventoryItemIsFoodDP")
    public void Eat_InventoryItemsAreFood_ItemRemovedFromInventory(Food food) {
        GameCharacter testCharacter = new GameCharacter("Max", 70, 800, CharacterState.Eating);
        Food testFood3 = new Food("Apple", 100.0, 5.0, 5, FoodState.Fresh, 56);

        //fill two elements with food items and eat the second element
        testCharacter.pickUpItem(testFood3);
        testCharacter.pickUpItem(food);
        testCharacter.eat(1);

        int expectedResult = 1;
        int actualResult = testCharacter.getInventorySize();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides non food items
    @DataProvider(name = "EatInventoryItemIsNotFoodDP")
    public Object[][] EatInventoryItemIsNotFoodDP() {
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);

        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);
        return new Object[][] {{testArmour}, {testItem}, {testWeapon}};
    }

    //Tests if the non food item remains in the inventory when the eat method is called on it
    @Test (dataProvider = "EatInventoryItemIsNotFoodDP")
    public void Eat_InventoryItemIsNotFood_ItemRemainsInInventory(Item testItem) {
        GameCharacter testCharacter = new GameCharacter("Max", 70, 800, CharacterState.Eating);
        //Add item to the inventory and attempt to eat it
        testCharacter.pickUpItem(testItem);
        testCharacter.eat(0);

        int expectedResult = 1;
        int actualResult = testCharacter.getInventorySize();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides foods with different food states and positive and negative calories, initial character health value
    //and the expected character health result
    @DataProvider(name = "EatDifferentFoodStatesDP")
    public Object[][] EatDifferentFoodStatesDP() {
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Food testFood2 = new Food("Mushroom", 100.0, 50.0, 5, FoodState.Fresh, -500);
        Food testFood3 = new Food("Steak", 100.0, 50.0, 5, FoodState.Stale, 500);
        Food testFood4 = new Food("Mushroom", 100.0, 50.0, 5, FoodState.Stale, -500);
        Food testFood5 = new Food("Steak", 100.0, 50.0, 5, FoodState.Mouldy, 500);
        Food testFood6 = new Food("Mushroom", 100.0, 50.0, 5, FoodState.Mouldy, -500);
        Food testFood7 = new Food("Steak", 100.0, 50.0, 5, FoodState.Rotten, 500);
        Food testFood8 = new Food("Mushroom", 100.0, 50.0, 5, FoodState.Rotten, -500);

        return new Object[][] {{testFood1, 50, 70},
                {testFood2, 50, 30},
                {testFood3, 50, 62},
                {testFood4, 50, 30},
                {testFood5, 50, 58},
                {testFood6, 50, 30},
                {testFood7, 50, 52},
                {testFood8, 50, 30}};
    }

    //Tests if the health is increased according to the calorie count and the state of the food
    @Test (dataProvider = "EatDifferentFoodStatesDP")
    public void Eat_DifferentFoodStates_HealthIncreasesCorrectly(Food food, int health, int expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Max", health, 800, CharacterState.Running);

        //Add food to the inventory and eat it
        testCharacter.pickUpItem(food);
        testCharacter.eat(0);

        int actualResult = testCharacter.getHealth();
        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides different item types and character states as expected results
    @DataProvider(name = "EatInventoryItemsDP")
    public Object[][] EatInventoryItemsDP() {
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testArmour, CharacterState.Idle}, {testWeapon, CharacterState.Idle}, {testFood1, CharacterState.Eating}, {testItem, CharacterState.Idle}};
    }

    //Tests if the state of the character changes when attempting to eat both a food item and a non food item
    @Test (dataProvider = "EatInventoryItemsDP")
    public void Eat_EatingFoodAndNonFoodItem_CharacterStateIsEating(Item testItem, CharacterState expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Max", 50, 800, CharacterState.Idle);

        testCharacter.pickUpItem(testItem);
        testCharacter.eat(0);

        //Stores the resulting character state
        CharacterState actualResult = testCharacter.getCharacterState();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides item, food, armour and weapon types and expected boolean results
    @DataProvider(name = "EatReturnValueDP")
    public Object[][] EatReturnValueDP() {
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testWeapon, false}, {testFood1, true}, {testArmour, false}, {testItem, false}};
    }

    //Tests if the eat method returns the correct value when called to eat different inventory items
    @Test (dataProvider = "EatReturnValueDP")
    public void Eat_EatingFoodAndNonFoodItem_EatMethodReturnsCorrectValue(Item testItem, boolean expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Max", 50, 800, CharacterState.Idle);

        testCharacter.pickUpItem(testItem);
        boolean actualResult = testCharacter.eat(0);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides different foods with positive calories
    @DataProvider(name = "HealthGreaterThenOneHundredDP")
    public Object[][] HealthGreaterThenOneHundredDP() {
        Food testFood1 = new Food("Apple", 100.0, 5.0, 5, FoodState.Fresh, 50);
        Food testFood2 = new Food("Pear", 100.0, 5.0, 5, FoodState.Fresh, 250);
        return new Object[][] {{testFood1}, {testFood2}};
    }

    //Tests if health remains at the 100 limit when the character eats food with enough calories to surpass the health limit
    @Test (dataProvider = "HealthGreaterThenOneHundredDP")
    public void Eat_HealthResultsInMoreThenTheLimit_HealthRemainsAtLimit(Food food) {
        GameCharacter testCharacter = new GameCharacter("Jax", 99, 909.0, CharacterState.Attacking);

        testCharacter.pickUpItem(food);
        testCharacter.eat(0);

        int expectedResult = 100;
        int actualResult = testCharacter.getHealth();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides foods with negative calories
    @DataProvider(name = "EatHealthLessThanZeroDP")
    public Object[][] EatHealthLessThanZeroDP() {
        Food testFood1 = new Food("Apple", 100.0, 5.0, 5, FoodState.Fresh, -50);
        Food testFood2 = new Food("Pear", 100.0, 5.0, 5, FoodState.Fresh, -1000);
        return new Object[][] {{testFood1}, {testFood2}};
    }

    //Tests if the health remains at 0 when there is enough negative calories eaten to drop below 0
    @Test (dataProvider = "EatHealthLessThanZeroDP")
    public void Eat_HealthResultsInLessThanZero_HealthRemainsAtZero(Food food) {
        GameCharacter testCharacter = new GameCharacter("Lu Khan", 1, 990.0, CharacterState.Attacking);

        testCharacter.pickUpItem(food);
        testCharacter.eat(0);

        int expectedResult = 0;
        int actualResult = testCharacter.getHealth();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides Food, armour, item and weapon types and expected boolean value results
    @DataProvider(name = "EquipWeaponIndexDP")
    public Object[][] EquipWeaponIndexDP() {
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testFood1, false}, {testArmour, false}, {testWeapon, true}, {testItem, false}};
    }

    //Tests if weapon and non weapon types will equip
    @Test (dataProvider = "EquipWeaponIndexDP")
    public void EquipWeapon_DifferentArgumentTypesProvided_CorrectValueReturned(Item testItem, boolean expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Snoopy", 1, 990.0, CharacterState.Attacking);

        testCharacter.pickUpItem(testItem);
        boolean actualResult = testCharacter.equipWeapon(0);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if weapon and non weapon types will unequip
    @Test (dataProvider = "EquipWeaponIndexDP")
    public void UnEquipWeapon_DifferentArgumentTypesProvided_OnlyWeaponUnequips(Item testItem, boolean expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Gohan", 1, 990.0, CharacterState.Attacking);

        testCharacter.pickUpItem(testItem);
        boolean actualResult = testCharacter.unEquipWeapon(0);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides method names, a starting character state and the expected state result
    @DataProvider(name = "StateChangeDP")
    public Object[][] StateChange() {
        return new Object[][] {{"walk", CharacterState.Running, CharacterState.Walking},
                {"run", CharacterState.Walking, CharacterState.Running},
                {"defend", CharacterState.Running, CharacterState.Defending},
                {"sleep", CharacterState.Defending, CharacterState.Sleeping},
                {"wakeUp", CharacterState.Sleeping, CharacterState.Idle},
                {"wakeUp", CharacterState.Walking, CharacterState.Walking}};
    }

    //Tests if character states will change when their respective methods are called (wakeUp() method only changes to idle if the character was sleeping)
    @Test (dataProvider = "StateChangeDP")
    public void CharacterStates_MethodsCalledToChangeCharacterStates_StatesChangeCorrectly(String methodName, CharacterState startingState, CharacterState expectedResult)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        GameCharacter testCharacter = new GameCharacter("Loki", 1, 99.0, startingState);
        GameCharacter.class.getDeclaredMethod(methodName).invoke(testCharacter);
        CharacterState actualResult = testCharacter.getCharacterState();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides Items that are below the total weight limit, a starting weight limit and the expected total item weight result
    @DataProvider(name = "PickUpItemUnderWeightDP")
    public Object[][] PickUpItemUnderWeightDP() {
        Food testFood1 = new Food("Steak", 4.5, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 150.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testFood1, 50.0, 54.5},
                {testArmour, 50.0, 250.0},
                {testItem, 50.0, 100.0},
                {testWeapon, 50.0, 200.0}};
    }

    //Tests if the total item weight increases accordingly
    @Test (dataProvider = "PickUpItemUnderWeightDP")
    public void PickUpItem_ItemIsUnderWeight_TotalWeightIncreases(Item item, double startingWeight, double expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Wolverine", 100, 250.0, CharacterState.Attacking);

        testCharacter.setTotalWeightOfItems(startingWeight);
        testCharacter.pickUpItem(item);

        double actualResult = testCharacter.getTotalWeightOfItems();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if picked up item adds to the inventory
    @Test (dataProvider = "PickUpItemUnderWeightDP")
    public void PickUpItem_ItemIsUnderWeight_ItemAddsToInventory(Item item, double startingWeight, double resultingWeight/*unused*/) {
        GameCharacter testCharacter = new GameCharacter("Wolverine", 100, 250.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(startingWeight);
        testCharacter.pickUpItem(item);

        Assert.assertTrue(testCharacter.getInventoryItem(item));
    }

    //Provides multiple item types in one test
    @DataProvider(name = "DifferentItemClassesDP")
    public Object[][] DifferentItemClassesDP() {
        Food testFood1 = new Food("Steak", 4.5, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 150.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testArmour, testFood1, testItem, testWeapon}};
    }

    //Tests if more then one item picked up adds to the total weight if the total weight is under
    @Test (dataProvider = "DifferentItemClassesDP")
    public void PickUpItem_MultipleItemsUnderWeight_ItemsAddToTotalWeight(Item item1, Item item2, Item item3, Item item4) {
        GameCharacter testCharacter = new GameCharacter("Wolverine", 100, 455.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(50.0);
        testCharacter.pickUpItem(item1);
        testCharacter.pickUpItem(item2);
        testCharacter.pickUpItem(item3);
        testCharacter.pickUpItem(item4);

        double actualResult = testCharacter.getTotalWeightOfItems();
        double expectedResult = 454.5;

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if items add to the total weight if the surpass the weight limit
    @Test (dataProvider = "DifferentItemClassesDP")
    public void PickUpItem_MultipleItemsUnderWeight_LastItemDoesNotAddToTotalWeight(Item item1, Item item2, Item item3, Item item4) {
        GameCharacter testCharacter = new GameCharacter("Wolverine", 100, 455.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(50.1);
        testCharacter.pickUpItem(item1);
        testCharacter.pickUpItem(item2);
        testCharacter.pickUpItem(item3);
        testCharacter.pickUpItem(item4);

        double actualResult = testCharacter.getTotalWeightOfItems();
        double expectedResult = 454.6;

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if items are added to the inventory after they surpass the weight limit
    @Test (dataProvider = "DifferentItemClassesDP")
    public void PickUpItem_MultipleItemsUnderWeight_LastItemDoesNotAddToInventory(Item item1, Item item2, Item item3, Item item4) {
        GameCharacter testCharacter = new GameCharacter("Wolverine", 100, 400.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(50.1);
        testCharacter.pickUpItem(item1);
        testCharacter.pickUpItem(item2);
        testCharacter.pickUpItem(item3);
        testCharacter.pickUpItem(item4);

        int actualResult = testCharacter.getInventorySize();
        int expectedResult = 3;

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides Items of different types and starting total item weight values
    @DataProvider(name = "PickUpItemOverWeightDP")
    public Object[][] PickUpItemOverWeightDP() {
        Food testFood1 = new Food("Steak", 4.5, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 150.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testArmour, 50.0}, {testFood1, 99.0}, {testItem, 50.5}, {testWeapon, 0.0}};
    }

    //Tests if items will add to the total weight if they exceed the weight limit
    @Test (dataProvider = "PickUpItemOverWeightDP")
    public void PickUpItem_ItemIsOverWeight_ItemWeightIsNotAddedToTotalWeight(Item item, double weight) {
        GameCharacter testCharacter = new GameCharacter("John Wayne", 100, 100.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(weight);
        testCharacter.pickUpItem(item);

        double actualResult = testCharacter.getTotalWeightOfItems();

        Assert.assertEquals(actualResult, weight);
    }

    //Tests if items are added to the inventory if they exceed the weight limit
    @Test (dataProvider = "PickUpItemOverWeightDP")
    public void PickUpItem_ItemIsOverWeight_ItemIsNotAddedToInventory(Item item, double weight) {
        GameCharacter testCharacter = new GameCharacter("Frodo", 100, 100.0, CharacterState.Attacking);
        testCharacter.setTotalWeightOfItems(weight);
        int expectedResult = testCharacter.getInventorySize();

        testCharacter.pickUpItem(item);
        int actualResult = testCharacter.getInventorySize();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides different item types
    @DataProvider(name = "DropItemDP")
    public Object[][] DropItemDP() {
        Food testFood1 = new Food("Steak", 4.5, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 150.0, 2.0, 5, 60,
                99, false);
        GameCharacter testCharacter = new GameCharacter("Hulk", 100, 500.0, CharacterState.Attacking);
        testCharacter.pickUpItem(testItem);
        testCharacter.pickUpItem(testFood1);
        testCharacter.pickUpItem(testWeapon);
        testCharacter.pickUpItem(testArmour);

        return new Object[][] {{testCharacter, testFood1},
                {testCharacter, testArmour},
                {testCharacter, testItem},
                {testCharacter, testWeapon}};
    }

    //Tests if total item weight decreases by the weight of the item dropped
    @Test (dataProvider = "DropItemDP")
    public void DropItem_ItemIsDropped_TotalItemWeightDecreasesAccordingly(GameCharacter testCharacter, Item item) {
        double expectedResult = testCharacter.getTotalWeightOfItems() - item.getItemWeight();
        testCharacter.dropItem(item);
        double actualResult = testCharacter.getTotalWeightOfItems();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if item dropped is removed from the inventory
    @Test (dataProvider = "DropItemDP")
    public void DropItem_ItemIsDropped_ItemIsRemovedFromInventory(GameCharacter testCharacter, Item item) {
        testCharacter.dropItem(item);
        Assert.assertFalse(testCharacter.getInventoryItem(item));
    }

    //Provides Food, armour, item and weapon types and expected boolean value results
    @DataProvider(name = "EquipArmourDP")
    public Object[][] EquipArmourDP() {
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);

        return new Object[][] {{testFood1, false}, {testArmour, true}, {testWeapon, false}, {testItem, false}};
    }

    //Tests if armour and non armour types will equip
    @Test (dataProvider = "EquipArmourDP")
    public void EquipArmour_DifferentArgumentTypesProvided_CorrectValueReturned(Item testItem, boolean expectedResult) {
        GameCharacter testCharacter = new GameCharacter("Gimli", 1, 500.0, CharacterState.Attacking);

        testCharacter.pickUpItem(testItem);
        boolean actualResult = testCharacter.equipArmour(0);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if armour and non armour types will unequip
    @Test
    public void RemoveArmour_ArmourEquipped_ArmourRemoved() {
        Armour testArmour = new Armour("Shield", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Legolas", 1, 500.0, CharacterState.Attacking);

        testCharacter.pickUpItem(testArmour);
        testCharacter.equipArmour(0);
        testCharacter.removeArmour(0);

        Assert.assertFalse(testArmour.isArmourEquipped());
    }

    //Provides two shields and a game character
    @DataProvider(name = "EquipShieldDP")
    public Object[][] EquipShieldDP() {
        Armour Shield1 = new Armour("Shield1", 200.0, 300.0, 20, 80,
                99, false, ArmourTypes.Hold, ArmourMaterial.Iron);
        Armour Shield2 = new Armour("Shield2", 200.0, 300.0, 20, 80,
                99, false, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Aragon", 1, 1000.0, CharacterState.Attacking);

        return new Object[][] {{Shield1, Shield2, testCharacter}};
    }

    //Tests if the shield is equipped while one is equipped already
    @Test (dataProvider = "EquipShieldDP")
    public void EquipArmour_EquipShieldWhileShieldArmourAlreadyHeld_NewShieldEquipped(Armour shield1, Armour shield2, GameCharacter testCharacter) {

        testCharacter.pickUpItem(shield1);
        testCharacter.equipArmour(0);
        testCharacter.pickUpItem(shield2);
        testCharacter.equipArmour(1);

        Assert.assertTrue(shield2.isArmourEquipped());
    }

    //Tests if the shield held is removed when another shield is being equipped
    @Test (dataProvider = "EquipShieldDP")
    public void EquipArmour_EquipShieldWhileShieldArmourAlreadyHeld_OldShieldRemoved(Armour shield1, Armour shield2, GameCharacter testCharacter) {

        testCharacter.pickUpItem(shield1);
        testCharacter.equipArmour(0);
        testCharacter.pickUpItem(shield2);
        testCharacter.equipArmour(1);;

        Assert.assertFalse(shield1.isArmourEquipped());
    }

    //Provides multiple wearable armours and a game character
    @DataProvider(name = "EquipWearableDP")
    public Object[][] EquipWearableDP() {
        Armour helmet = new Armour("Helmet", 600.0, 300.0, 20, 90,
                99, false, ArmourTypes.Wearable, ArmourMaterial.Iron);
        Armour gloves = new Armour("Gloves", 200.0, 300.0, 20, 80,
                99, false, ArmourTypes.Wearable, ArmourMaterial.Iron);
        Armour mithril = new Armour("Mithril", 1.0, 300.0, 1000, 99,
                99, false, ArmourTypes.Wearable, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 1000.0, CharacterState.Attacking);

        return new Object[][] {{helmet, gloves, mithril, testCharacter}};
    }

    //Tests if multiple wearable armours can be equipped at once
    @Test (dataProvider = "EquipWearableDP")
    public void EquipArmour_EquipMultipleWearableArmours_EachWearableEquips(Armour helmet, Armour gloves, Armour mithril, GameCharacter testCharacter) {

        testCharacter.pickUpItem(gloves);
        testCharacter.equipArmour(0);
        testCharacter.pickUpItem(helmet);
        testCharacter.equipArmour(1);
        testCharacter.pickUpItem(mithril);
        testCharacter.equipArmour(2);

        Assert.assertTrue(helmet.isArmourEquipped() && mithril.isArmourEquipped() && gloves.isArmourEquipped());
    }

    //Provides multiple armour instances, different item types, a game character and the expected defence total
    @DataProvider(name = "GetArmourDefenceDP")
    public Object[][] GetArmourDefenceDP() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Armour gloves = new Armour("Gloves", 200.0, 300.0, 20, 80,
                99, false, ArmourTypes.Hold, ArmourMaterial.Iron);
        Armour mithril = new Armour("Mithril", 1.0, 300.0, 1000, 99,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Armour shield = new Armour("Shield1", 200.0, 300.0, 20, 80,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);

        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Attacking);

        return new Object[][] {{helmet, gloves, mithril, shield, testFood1, testItem, testWeapon, testCharacter, 269.0}};
    }

    //Tests the total defence value of only armours instances that are equipped
    @Test (dataProvider = "GetArmourDefenceDP")
    public void GetCharacterDefence_MultipleArmoursInInventory_CharacterDefenceEqual(Armour helmet, Armour gloves, Armour mithril, Armour shield, Food food, Item item,
                                                                                     Weapon weapon, GameCharacter testCharacter, double expectedResult) {
        testCharacter.pickUpItem(helmet);
        testCharacter.pickUpItem(food);
        testCharacter.pickUpItem(gloves);
        testCharacter.pickUpItem(weapon);
        testCharacter.pickUpItem(item);
        testCharacter.pickUpItem(shield);
        testCharacter.pickUpItem(mithril);
        System.out.println(testCharacter.getCharacterDefence());

        double actualResult = testCharacter.getCharacterDefence();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides multiple weapon instances, different item types, a game character and the expected total attack value
    @DataProvider(name = "GetTotalAttackDP")
    public Object[][] GetTotalAttackDP() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        Weapon sword = new Weapon("Sword", 200.0, 2.0, 5, 60,
                99, false);
        Weapon spear = new Weapon("Spear", 150.0, 2.0, 5, 60,
                99, false);
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);
        Item testItem = new Item("Rope", 50.0, 60.0, 10);
        Weapon axe = new Weapon("Axe", 250.0, 2.0, 5, 60,
                99, false);

        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Attacking);

        return new Object[][] {{helmet, testFood1, testItem, sword, spear, axe, testCharacter, 120}};
    }

    //Tests if only equipped weapons add to the total attack value
    @Test (dataProvider = "GetTotalAttackDP")
    public void GetCharacterAttackValue_ItemsProvided_ValueEqualsTotalAttackValueOfWeaponsEquipped(Armour helmet, Food food, Item item,
                                                                                                   Weapon sword, Weapon spear, Weapon axe, GameCharacter testCharacter, int expectedResult) {
        testCharacter.pickUpItem(helmet);
        testCharacter.pickUpItem(food);
        testCharacter.pickUpItem(sword);
        testCharacter.pickUpItem(spear);
        testCharacter.pickUpItem(item);
        testCharacter.pickUpItem(axe);

        //Equip sword and axe
        testCharacter.equipWeapon(2);
        testCharacter.equipWeapon(5);

        int actualResult = testCharacter.getCharacterAttackValue();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides weapon instances and game characters
    @DataProvider (name = "AttackStringDP")
    public Object[][] AttackStringDP() {
        Weapon spork = new Weapon("Spork", 200.0, 2.0, 5, 60,
                99, false);
        Weapon rubberChicken = new Weapon("Rubber Chicken", 150.0, 2.0, 5, 60,
                99, false);
        GameCharacter yourCharacter = new GameCharacter("Elmo", 100, 10000.0, CharacterState.Attacking);
        GameCharacter targetCharacter = new GameCharacter("Big Bird", 100, 10000.0, CharacterState.Attacking);

        return new Object[][] {{spork, rubberChicken, yourCharacter, targetCharacter}};
    }

    //Tests is attack method returns two weapons correctly
    @Test (dataProvider = "AttackStringDP")
    public void Attack_TwoWeaponsEquipped_StringPrintsTwoWeaponNames(Weapon spork, Weapon rubberChicken, GameCharacter yourCharacter, GameCharacter targetCharacter) {
        yourCharacter.pickUpItem(spork);
        yourCharacter.pickUpItem(rubberChicken);
        yourCharacter.equipWeapon(0);
        yourCharacter.equipWeapon(1);

        String expectedResult = "Elmo is attacking Big Bird with a Spork and a Rubber Chicken";
        String actualResult =  yourCharacter.attack(targetCharacter);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if Attack method returns one weapon correctly
    @Test (dataProvider = "AttackStringDP")
    public void Attack_OneWeaponEquipped_StringPrintsOneWeaponName(Weapon spork, Weapon rubberChicken, GameCharacter yourCharacter, GameCharacter targetCharacter) {
        yourCharacter.pickUpItem(spork);
        yourCharacter.pickUpItem(rubberChicken);
        yourCharacter.equipWeapon(1);

        String expectedResult = "Elmo is attacking Big Bird with a Rubber Chicken";
        String actualResult =  yourCharacter.attack(targetCharacter);

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if character name can be set if character is dead
    @Test
    public void SetCharacterName_CharacterIsDead_NoNameSet(){
        GameCharacter testCharacter = new GameCharacter("Goku", 0, 99.0, CharacterState.Dead);
        String expectedResult = "Goku";
        testCharacter.setCharacterName("Vision");
        testCharacter.setCharacterState(CharacterState.Idle);

        String actualResult = testCharacter.getCharacterName();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if character health can be set if character is dead
    @Test
    public void SetHealth_CharacterIsDead_NoHealthSet(){
        GameCharacter testCharacter = new GameCharacter("John Rambo", 0, 99.0, CharacterState.Dead);
        testCharacter.setHealth(50);

        int expectedResult = 0;
        int actualResult = testCharacter.getHealth();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if character weight limit can be set if character is dead
    @Test
    public void SetWeightLimit_CharacterIsDead_NoWeightLimitSet(){
        GameCharacter testCharacter = new GameCharacter("John Rambo", 0, 99.0, CharacterState.Dead);
        testCharacter.setWeightLimit(50);

        double expectedResult = 99;
        double actualResult = testCharacter.getWeightLimit();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if total weight of items can be set if character is dead
    @Test
    public void SetTotalWeightOfItems_CharacterIsDead_NoWeightSet(){
        GameCharacter testCharacter = new GameCharacter("John Rambo", 0, 99.0, CharacterState.Idle);
        testCharacter.setTotalWeightOfItems(50);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.setTotalWeightOfItems(90);

        double expectedResult = 50;
        double actualResult = testCharacter.getTotalWeightOfItems();

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Tests if the eat method will do anything if character is dead
    @Test
    public void Eat_CharacterIsDead_CharacterCantEat() {
        GameCharacter testCharacter = new GameCharacter("Max", 50, 800, CharacterState.Idle);
        Food testFood1 = new Food("Steak", 100.0, 50.0, 5, FoodState.Fresh, 500);

        testCharacter.pickUpItem(testFood1);
        testCharacter.setCharacterState(CharacterState.Dead);
        boolean actualResult = testCharacter.eat(0);

        Assert.assertFalse(actualResult);
    }

    //Tests if the attack method will do anything if character is dead
    @Test
    public void Attack_CharacterIsDead_ReturnsNull() {
        GameCharacter testCharacter = new GameCharacter("Batman", 100, 10000.0, CharacterState.Dead);
        GameCharacter targetCharacter = new GameCharacter("Joker", 100, 10000.0, CharacterState.Idle);

        String actualResult = testCharacter.attack(targetCharacter);

        Assert.assertNull(actualResult);
    }

    //Tests if a weapon will equip if character is dead
    @Test
    public void EquipWeapon_CharacterIsDead_WeaponNotEquipped() {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Idle);

        testCharacter.pickUpItem(testWeapon);
        testCharacter.unEquipWeapon(0);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.equipWeapon(0);
        boolean actualResult = testWeapon.isWeaponEquipped();

        Assert.assertFalse(actualResult);
    }

    //Tests if a weapon will unequip if character is dead
    @Test
    public void UnequipWeapon_CharacterIsDead_WeaponRemainsEquipped() {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60,
                99, false);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Idle);

        testCharacter.pickUpItem(testWeapon);
        testCharacter.equipWeapon(0);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.unEquipWeapon(0);
        boolean actualResult = testWeapon.isWeaponEquipped();

        Assert.assertTrue(actualResult);
    }

    //Tests if a armour will equip if character is dead
    @Test
    public void EquipArmour_CharacterIsDead_ArmourNotEquipped() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Idle);

        testCharacter.pickUpItem(helmet);
        testCharacter.removeArmour(0);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.equipArmour(0);
        boolean actualResult = helmet.isArmourEquipped();

        Assert.assertFalse(actualResult);
    }

    //Tests if a armour will unequip if character is dead
    @Test
    public void RemoveArmour_CharacterIsDead_ArmourRemainsEquipped() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 100, 10000.0, CharacterState.Idle);

        testCharacter.pickUpItem(helmet);
        testCharacter.equipArmour(0);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.removeArmour(0);
        boolean actualResult = helmet.isArmourEquipped();

        Assert.assertTrue(actualResult);
    }

    //Tests if character can pick up items if character is dead
    @Test
    public void PickUpItem_CharacterDead_ItemNotPickedUp() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 0, 10000.0, CharacterState.Dead);

        testCharacter.pickUpItem(helmet);
        boolean actualResult = testCharacter.getInventoryItem(helmet);

        Assert.assertFalse(actualResult);
    }

    //Tests if character can drop items if character is dead
    @Test
    public void DropItem_CharacterDead_ItemNotDropped() {
        Armour helmet = new Armour("Helmet", 450.0, 300.0, 20, 90,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        GameCharacter testCharacter = new GameCharacter("Gandalf", 50, 10000.0, CharacterState.Idle);

        testCharacter.pickUpItem(helmet);
        testCharacter.setCharacterState(CharacterState.Dead);
        testCharacter.dropItem(helmet);
        boolean actualResult = testCharacter.getInventoryItem(helmet);

        Assert.assertTrue(actualResult);
    }

    //Tests if character dies when health is set to zero
    @Test
    public void setHealth_HealthSetToZero_CharacterDies() {
        GameCharacter testCharacter = new GameCharacter("Gandalf", 50, 10000.0, CharacterState.Idle);

        testCharacter.setHealth(0);
        CharacterState actualResult = testCharacter.getCharacterState();
        CharacterState expectedResult = CharacterState.Dead;

        Assert.assertEquals(actualResult, expectedResult);
    }

    //Provides gamev character, a starting health and an expected resulting health
    @DataProvider (name = "SleepHealthIncreaseDP")
    public Object[][] SleepHealthIncreaseDP() {
        GameCharacter testCharacter = new GameCharacter("Witcher", 10, 10000.0, CharacterState.Attacking);
        return new Object[][] {{testCharacter, 50, 70}, {testCharacter, 90, 100}, {testCharacter, 100, 100}};
    }

    //Tests if sleep method increases health correctly
    @Test (dataProvider = "SleepHealthIncreaseDP")
    public void Sleep_SleepMethodCalled_HealthIncreasesCorrectly(GameCharacter testCharacter, int health, int expectedResult) {
        testCharacter.setHealth(health);
        testCharacter.sleep();
        int actualResult = testCharacter.getHealth();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
    