package worksheet5.weapons;

import worksheet5.WeaponEffects;
import worksheet5.attack.BludgeonWeapon;

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
