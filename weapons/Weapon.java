package worksheet5.weapons;

import worksheet5.WeaponEffects;

public abstract class Weapon {
    protected final int DMG;
    protected final double hitrate;
    protected final WeaponEffects weaponEffect;
    protected final double effectRate;

    public Weapon(int DMG, double hitrate, WeaponEffects weaponEffect, double effectRate) {
        this.DMG = DMG;
        this.hitrate = hitrate;
        this.weaponEffect = weaponEffect;
        this.effectRate = effectRate;
    }

    public abstract void attack();
    public int getDMG() {
        return this.DMG;
    }

    public double getHitrate() {
        return hitrate;
    }

    public WeaponEffects getWeaponEffect() {
        return weaponEffect;
    }

    public double getEffectRate() {
        return effectRate;
    }
}
