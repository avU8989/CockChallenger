package worksheet5.weapons;

import worksheet5.WeaponEffects;
import worksheet5.attack.MagicWeapon;

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
