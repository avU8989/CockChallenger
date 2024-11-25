package cock.challenger.combatlog;

public interface HealthObserver {
    void update(String attackerName, String actionDetails, int currentHp);
}
