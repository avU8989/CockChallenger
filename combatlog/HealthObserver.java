package worksheet5.combatlog;

public interface HealthObserver {
    void update(String attackerName, String actionDetails, int currentHp);
}
