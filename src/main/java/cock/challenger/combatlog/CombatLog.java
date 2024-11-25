package cock.challenger.combatlog;

public class CombatLog implements CombatObserver{
    @Override
    public void update(String characterName, String combatDetails) {
        System.out.println("[Combat Log] " + characterName + ": " + combatDetails);
    }
}
