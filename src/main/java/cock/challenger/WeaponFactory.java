package cock.challenger;


import cock.challenger.weapons.*;

public class WeaponFactory {
    public static Weapon createSlicingWeapon(WeaponType weaponType){
        return switch (weaponType){
            case AXE -> new Axe(50, 0.3, WeaponEffects.NONE, 0);
            case SWORD -> new Sword(15, 0.8, WeaponEffects.BLEEDING, 0.6);
            case DAGGER -> new Dagger(30, 0.75, WeaponEffects.POISONED, 0.4);
            default -> throw new IllegalArgumentException("Unknown Weapon");
        };
    }

    public static Weapon createBludgeonWeapon(WeaponType weaponType){
        return switch (weaponType) {
            case CLUB -> new Club(35, 0.4, WeaponEffects.CONFUSION, 0.3);
            default -> throw new IllegalArgumentException("Invalid magic weapon: " + weaponType);
        };
    }

    public static Weapon createMagicWeapon(WeaponType weaponType) {
        return switch (weaponType) {
            case MAGIC_WAND -> new MagicWand(30, 0.8, WeaponEffects.BURNING, 0.1);
            default -> throw new IllegalArgumentException("Invalid magic weapon: " + weaponType);
        };
    }
}
