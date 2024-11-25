package cock.challenger.combatlog;

import java.util.ArrayList;
import java.util.List;

public interface CharacterSubject {
    void registerCombatObserver(CombatObserver combatObserver);
    void removeCombatObserver(CombatObserver combatObserver);
    void notifyCombatObservers(String actionStatus);

    void registerHealthObserver(HealthObserver combatObserver);
    void removeHeatlhObserver(HealthObserver combatObserver);
    void notifyHealthObservers(String attackerName, String actionDetails, int currentHp);
}
