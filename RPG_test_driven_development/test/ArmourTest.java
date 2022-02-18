/*
 * ArmourTest
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

public class ArmourTest {

    //Provides integer outside the [1-100] range
    @DataProvider(name = "SetArmourDefenceAndHealthDP")
    public Object[][] SetArmourDefenceAndHealthDP() {
        return new Object[][] {{0}, {-10}, {101}, {200}};
    }

    //Tests if an illegal argument exception is thrown if armour defence is set out of bounds [1-100]
    @Test (dataProvider = "SetArmourDefenceAndHealthDP", expectedExceptions = IllegalArgumentException.class)
    public void SetArmourDefence_InvalidArgumentProvided_ThrowsIllegalArgumentException(int defence) {

        Armour testArmour = new Armour("Shield", 5.0, 2.0, 5, 6,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        testArmour.setArmourDefence(defence);
    }

    //Tests if an illegal argument exception is thrown if armour health is set out of bounds [1-100]
    @Test (dataProvider = "SetArmourDefenceAndHealthDP", expectedExceptions = IllegalArgumentException.class)
    public void SetArmourHealth_InvalidArgumentProvided_ThrowsIllegalArgumentException(int health) {

        Armour testArmour = new Armour("Shield", 5.0, 2.0, 5, 6,
                99, true, ArmourTypes.Hold, ArmourMaterial.Iron);
        testArmour.setArmourHealth(health);
    }

    //Provides an armour constructor with one illegal argument per test
    @DataProvider(name = "ArmourConstructorDP")
    public Object[][] ArmourConstructorDP() {
        return new Object[][] {{0, 1, true, ArmourTypes.Hold, ArmourMaterial.Steel},
                {101, 1, true, ArmourTypes.Hold, ArmourMaterial.Steel},
                {1, 0, true, ArmourTypes.Hold, ArmourMaterial.Steel},
                {1, 101, true, ArmourTypes.Hold, ArmourMaterial.Steel}};
    }

    //Tests if an illegal argument is thrown when constructing armour with an illegal argument
    @Test (dataProvider = "ArmourConstructorDP", expectedExceptions = IllegalArgumentException.class)
    public void ArmourConstructor_InvalidArgumentsProvided_IllegalArgumentExceptionThrown(int armourDefence,
                                                                                          int armourHealth,
                                                                                          boolean equipped,
                                                                                          ArmourTypes armourType,
                                                                                          ArmourMaterial armourMaterial) {

        Armour testArmour = new Armour("Shield", 5.0, 2.0, 5, armourDefence,
                armourHealth, equipped, armourType, armourMaterial);
    }

    //Provides two opposing armour types per test
    @DataProvider(name = "SetArmourTypesDP")
    public Object[][] SetArmourTypesDP() {
        return new Object[][]{{ArmourTypes.Hold, ArmourTypes.Wearable}, {ArmourTypes.Wearable, ArmourTypes.Hold}};
    }

    //Tests if armour types will remain unchanged
    @Test (dataProvider = "SetArmourTypesDP")
    public void SetArmourTypes_SetDifferentArmourType_ArmourTypesDontChange(ArmourTypes armourType1,
                                                                            ArmourTypes armourType2) {
        Armour testArmour = new Armour("Shield", 5.0, 2.0, 5, 10,
                10, true, armourType1, ArmourMaterial.Iron);

        testArmour.setArmourType_(armourType2);
        Assert.assertEquals(testArmour.getArmourType(), armourType1);
    }

    //Provides alternating defence materials
    @DataProvider(name = "SetArmourMaterialDP")
    public Object[][] SetArmourMaterialDP() {
        return new Object[][]{{ArmourMaterial.Steel, ArmourMaterial.Cardboard},
                {ArmourMaterial.Cardboard, ArmourMaterial.Iron},
                {ArmourMaterial.Iron, ArmourMaterial.Leather},
                {ArmourMaterial.Leather, ArmourMaterial.Wood},
                {ArmourMaterial.Wood, ArmourMaterial.Steel},};
    }

    //Tests to ensure armour materials remain unchanged
    @Test (dataProvider = "SetArmourMaterialDP")
    public void SetArmourMaterial_SetDifferentArmourMaterials_ArmourMaterialsDontChange(ArmourMaterial armourMaterial1, ArmourMaterial armourMaterial2) {
        Armour testArmour = new Armour("Shield", 5.0, 2.0, 5, 10, 10, true, ArmourTypes.Hold, armourMaterial1);
        testArmour.setArmourMaterial(armourMaterial2);
        Assert.assertEquals(testArmour.getArmourMaterial(), armourMaterial1);

        //Gives Printed Visual of Test
        //System.out.println("testArmour is: " + testArmour.getArmourMaterial() + ", Material attempted to change t0: " + armourMaterial2);
    }


}