package cock.challenger.weapons;

import cock.challenger.WeaponEffects;
import cock.challenger.attack.SlicingWeapon;

public class Axe extends Weapon implements SlicingWeapon {
    private final int DMG = 100;

    public Axe(int DMG, double hitrate, WeaponEffects weaponEffect, double effectRate) {
        super(DMG, hitrate, weaponEffect, effectRate);
    }

    @Override
    public void attack() {
        this.slice();
    }

    @Override
    public void slice() {
        System.out.println("Axe is dealing 20 dmg");
    }
}
