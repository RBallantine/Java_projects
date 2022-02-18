/*
 * WeaponTest
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


public class WeaponTest {

    //Provides integer values outside the range of [1-100]
    @DataProvider(name = "SetInvalidWeaponHitStrengthAndWeaponHealthDP")
    public Object[][] SetInvalidWeaponHitStrengthAndWeaponHealthDP() {
        return new Object[][] {{0}, {-1}, {101}, {-100}};
    }

    //Tests if an exception is thrown when setting the weapon hit strength outside the [1-100] range
    @Test (dataProvider = "SetInvalidWeaponHitStrengthAndWeaponHealthDP", expectedExceptions = IllegalArgumentException.class)
    public void SetWeaponHitStrength_InvalidArgumentsProvided_IllegalArgumentsExceptionThrown(int hitStrength) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setWeaponHitStrength(hitStrength);
    }

    //Provides integer values inside the [1-100] range
    @DataProvider(name = "SetValidWeaponHitStrengthAndHealthDP")
    public Object[][] SetValidWeaponHitStrengthAndHealthDP() {
        return new Object[][] {{1}, {50}, {100}};
    }

    //Tests if set weapon hit strength sets correctly
    @Test (dataProvider = "SetValidWeaponHitStrengthAndHealthDP")
    public void SetWeaponHitStrength_ValidArgumentsProvided_ValuesAreEqual(int hitStrength) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setWeaponHitStrength(hitStrength);
        int actualResult = testWeapon.getWeaponHitStrength();

        Assert.assertEquals(actualResult, hitStrength);
    }

    //Tests if an exception is thrown setting the weapon health out of bounds
    @Test (dataProvider = "SetInvalidWeaponHitStrengthAndWeaponHealthDP", expectedExceptions = IllegalArgumentException.class)
    public void SetWeaponHealth_InvalidArgumentsProvided_IllegalArgumentsExceptionThrown(int weaponHealth) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setWeaponHealth(weaponHealth);
    }

    //Tests if the set health sets health values correctly
    @Test (dataProvider = "SetValidWeaponHitStrengthAndHealthDP")
    public void SetWeaponHealth_ValidArgumentsProvided_ValuesAreEqual(int weaponHealth) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setWeaponHealth(weaponHealth);
        int actualResult = testWeapon.getWeaponHealth();

        Assert.assertEquals(actualResult, weaponHealth);
    }

    //Provides boolean values
    @DataProvider(name = "SetDoubleHandedAndWeaponEquippedDP")
    public Object[][] SetDoubleHandedAndWeaponEquippedDP() {
        return new Object[][] {{true}, {false}};
    }

    //Tests if set double handed method will set boolean values correctly
    @Test (dataProvider = "SetDoubleHandedAndWeaponEquippedDP")
    public void SetDoubleHanded_ValidArgumentsProvided_ValuesAreEqual(boolean doubleHanded) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setDoubleHanded(doubleHanded);
        boolean actualResult = testWeapon.isDoubleHanded();

        Assert.assertEquals(actualResult, doubleHanded);
    }

    //Tests if set weapon equipped sets boolean values correctly
    @Test (dataProvider = "SetDoubleHandedAndWeaponEquippedDP")
    public void SetWeaponEquipped_ValidArgumentsProvided_ValuesAreEqual(boolean equipped) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, 60, 99, false);
        testWeapon.setWeaponEquipped(equipped);
        boolean actualResult = testWeapon.isWeaponEquipped();

        Assert.assertEquals(actualResult, equipped);
    }

    //Provides weapon constructor values with one invalid value per test
    @DataProvider(name = "WeaponConstructorDP")
    public Object[][] WeaponConstructorDP() {
        //The damage the weapon does. Must be in range [1..100]
        //The health of the weapon. Must be in range [1..100]
        return new Object[][] {{0, 1},
            {-1, 1},
            {101, 1},
            {1, 0},
            {1, -1},
            {1, 101}};
    }

    //Tests if weapon constructor throws an when constructing with an invalid argument
    @Test (dataProvider = "WeaponConstructorDP", expectedExceptions = IllegalArgumentException.class)
    public void WeaponConstructor_InvalidArgumentsProvided_IllegalArgumentExceptionThrown(int weaponHitStrength, int weaponHealth) {
        Weapon testWeapon = new Weapon("Sword", 2.0, 2.0, 5, weaponHitStrength, weaponHealth, true);
    }
}