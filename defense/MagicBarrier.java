package worksheet5.defense;

import worksheet5.attack.MagicWeapon;

public class MagicBarrier implements DefenseStrategy {
    @Override
    public void defend() {
        System.out.println("Magic Barrier absorbed 20 dmg");
    }
}
