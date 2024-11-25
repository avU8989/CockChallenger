package cock.challenger.defense;


public class MagicBarrier implements DefenseStrategy {
    @Override
    public void defend() {
        System.out.println("Magic Barrier absorbed 20 dmg");
    }
}
