package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Weapon;

public class WeaponTest {
    @Test
    public void testWeapon() {
        Weapon w = new Weapon(0, 0);
        assertEquals(w.useItem(), true);
        assertEquals(w.useItem(), true);
        assertEquals(w.useItem(), true);
        assertEquals(w.useItem(), true);
        assertEquals(w.useItem(), true);
        assertEquals(w.useItem(), false);
        assertEquals(w.useItem(), false);
    }
}