package worksheet5.healthtracker;

import worksheet5.combatlog.CombatObserver;
import worksheet5.combatlog.HealthObserver;

public class HealthTracker implements HealthObserver {
    @Override
    public void update(String attackerName, String actionDetails, int currentHp) {
        System.out.println(actionDetails);
        System.out.print("[HP Bar] " + attackerName + " HP: ");
        int hpBarLength = currentHp / 80; // Scale 8000 HP to a bar of 10 segments.
        for (int i = 0; i < hpBarLength; i++) {
            System.out.print("\u2588");
        }
        for (int i = hpBarLength; i < 10; i++) {
            System.out.print("-");
        }
        System.out.println(" (" + currentHp + " HP)");
    }
}
