package cock.challenger.weapons;

import cock.challenger.WeaponEffects;
import cock.challenger.attack.BludgeonWeapon;

public class Club extends Weapon implements BludgeonWeapon {

    public Club(int DMG, double hitrate, WeaponEffects weaponEffect, double effectRate) {
        super(DMG, hitrate, weaponEffect, effectRate);
    }

    @Override
    public void attack() {
        this.bonk();
    }

    @Override
    public void bonk() {
        System.out.println("BONK deals " + DMG + " with the status effect " + weaponEffect);
    }
}
