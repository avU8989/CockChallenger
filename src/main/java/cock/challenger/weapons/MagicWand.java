package cock.challenger.weapons;

import cock.challenger.WeaponEffects;
import cock.challenger.attack.MagicWeapon;


public class MagicWand extends Weapon implements MagicWeapon {
    public MagicWand(int DMG, double hitrate, WeaponEffects weaponEffect, double effectRate) {
        super(DMG, hitrate, weaponEffect, effectRate);
    }

    @Override
    public void cast() {
        System.out.println("Casting Fireball and deals " + DMG + " dmg");
    }

    @Override
    public void attack() {
        this.cast();
    }
}
