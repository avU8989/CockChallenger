package worksheet5.defense;

public class Shield implements DefenseStrategy{
    @Override
    public void defend() {
        System.out.println("Shield blocked 20 dmg");
    }
}