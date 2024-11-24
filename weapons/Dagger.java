package worksheet5.weapons;

import worksheet5.WeaponEffects;
import worksheet5.attack.SlicingWeapon;

public class Dagger extends Weapon implements SlicingWeapon {
    public Dagger(int DMG, double hitrate, WeaponEffects weaponEffect, double effectRate) {
        super(DMG, hitrate, weaponEffect, effectRate);
    }

    @Override
    public void attack() {
        this.slice();
    }

    @Override
    public void slice() {
        System.out.println("Dagger dealt 10 dmg and poisoned the enemy");
    }

}
