package worksheet5.figures;

import worksheet5.WeaponEffects;
import worksheet5.combatlog.CharacterSubject;
import worksheet5.combatlog.CombatObserver;
import worksheet5.combatlog.HealthObserver;
import worksheet5.defense.DefenseStrategy;
import worksheet5.exception.AttackException;
import worksheet5.exception.CombatException;
import worksheet5.exception.MissingWeaponException;
import worksheet5.playerstatus.PlayerDisadvantage;
import worksheet5.weapons.Weapon;

import java.util.*;

public abstract class PlayerFigure implements CharacterSubject {
    protected int MAX_HP = 800;
    protected static final int BASE_DEFENSE_CHANCE = 10;
    protected boolean isAlive = true;
    private Map<WeaponEffects, PlayerDisadvantage> debuff = new HashMap<>();
    protected Weapon weapon;
    private DefenseStrategy defenseStrategy;
    private List<CombatObserver> combatObservers = new ArrayList<>();
    private List<HealthObserver> healthObservers = new ArrayList<>();

    public PlayerFigure(Weapon weapon){
        this.weapon = weapon;
    }

    public boolean defend() {
        Random random = new Random();
        boolean defenseSuccessful = random.nextInt(100) < BASE_DEFENSE_CHANCE;

        if (defenseSuccessful && defenseStrategy != null) {
            System.out.println(getClass().getSimpleName() + " is defending: ");
            defenseStrategy.defend();
            notifyCombatObservers("blocked the attack using " + defenseStrategy.getClass().getSimpleName());
            return true;
        } else {
            System.out.println(getClass().getSimpleName() + " failed to defend.");
            notifyCombatObservers("failed to block the attack.");
            return false;
        }
    }

    public void takeDamage(WeaponEffects weaponEffect, int damage) {
        if (damage < 0) {
            throw new CombatException("Damage cannot be negative");
        }
        MAX_HP -= damage;
        if (MAX_HP <= 0) {
            MAX_HP = 0;
            isAlive = false;
            notifyCombatObservers("lost");
            System.out.println("Mission failed we'll get em next time");
        }
        System.out.println(getClass().getSimpleName() + " took " + damage + " damage!");
        notifyHealthObservers(getClass().getSimpleName(), "Took " + damage + " damage", MAX_HP);


        //effecting the playerfigure based on the enemy
        if(this.doesItEffect()){
            System.out.println("IT IS AFFECCTING");
            notifyCombatObservers(getClass().getSimpleName() + " was " + weaponEffect + "!");
            switch( weaponEffect){
                case CONFUSION -> this.debuff.put(WeaponEffects.CONFUSION, PlayerDisadvantage.HITRATE);
                case POISONED -> this.debuff.put(WeaponEffects.POISONED, PlayerDisadvantage.HP);
                case BLEEDING ->  this.debuff.put(WeaponEffects.BLEEDING,PlayerDisadvantage.HP);
                case BURNING ->  this.debuff.put(WeaponEffects.BURNING,PlayerDisadvantage.HP);
                default ->this.debuff.put(WeaponEffects.NONE,PlayerDisadvantage.NONE);
            }
        }

        //if we debuff is hitrate or hp do something
        debuffDetection();
    }

    private void debuffDetection(){
        for(Map.Entry<WeaponEffects, PlayerDisadvantage> entry : this.debuff.entrySet()){
            if(entry.getValue() == PlayerDisadvantage.HP){
                switch(entry.getKey()){
                    case BLEEDING -> {
                        MAX_HP -= MAX_HP * 0.01;
                        notifyHealthObservers(getClass().getSimpleName(), "Took " + entry.getKey() + " damage", MAX_HP);
                        break;
                    }
                    case POISONED -> {
                        MAX_HP -= MAX_HP * 0.12;
                        notifyHealthObservers(getClass().getSimpleName(), "Took " + entry.getKey() + " damage", MAX_HP);
                        break;
                    }
                    case BURNING -> {
                        MAX_HP -= MAX_HP * 0.05;
                        notifyHealthObservers(getClass().getSimpleName(), "Took " + entry.getKey() + " damage", MAX_HP);
                        break;
                    }
                }

            }
        }
    }

    public void attack(PlayerFigure enemy) {
        if(!enemy.isAlive){
            throw new AttackException("Enemy is dead");
        }

        if(this.weapon == null){
            throw new MissingWeaponException(getClass().getSimpleName() + " doesnt have a weapon");
        }
        System.out.print(getClass().getSimpleName() + " is attacking: ");

        if(!this.doesItHit()){
            this.notifyCombatObservers(getClass().getSimpleName() + " missed");
        }else{
            System.out.println();
            notifyCombatObservers("used " + this.weapon.getClass().getSimpleName());

            this.weapon.attack();

            // Let the opponent defend
            if (enemy.hasDefenseStrategy()) {
                boolean defenseSuccessful = enemy.defend();
                if (!defenseSuccessful) {
                    enemy.takeDamage(this.getWeapon().getWeaponEffect(), this.weapon.getDMG());
                }
            } else {
                enemy.takeDamage(this.getWeapon().getWeaponEffect(), this.weapon.getDMG());
            }
        }
    }

    protected boolean doesItHit(){
        Random random = new Random();
        double rngHitRate = random.nextDouble();
        double currentHitrate = this.weapon.getHitrate();
        //wenn confused wird waffe hitrate halbiert
        if(this.debuff.containsKey(WeaponEffects.CONFUSION)){
            currentHitrate -= currentHitrate * 0.5; // halbiert die hitrate

        }
        if(currentHitrate <= rngHitRate){
            return false;
        }

        return true;
    }

    public int getMAX_HP() {
        return MAX_HP;
    }

    protected boolean doesItEffect(){
        Random random = new Random();
        double rngEffectRate = random.nextDouble();

        if(this.weapon.getEffectRate() <= rngEffectRate){
            return false;
        }else{
            return true;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean hasDefenseStrategy() {
        return defenseStrategy != null;
    }

    public void setDefenseStrategy(DefenseStrategy defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }

    @Override
    public void registerCombatObserver(CombatObserver combatObserver) {
        combatObservers.add(combatObserver);
    }

    @Override
    public void removeCombatObserver(CombatObserver combatObserver) {
        combatObservers.remove(combatObserver);
    }

    @Override
    public void notifyCombatObservers(String actionStatus) {
        for (CombatObserver observer : combatObservers) {
            observer.update(getClass().getSimpleName(), actionStatus);
        }
    }

    @Override
    public void registerHealthObserver(HealthObserver healthObserver) {
        healthObservers.add(healthObserver);
    }

    @Override
    public void removeHeatlhObserver(HealthObserver healthObserver) {
        healthObservers.remove(healthObserver);
    }

    @Override
    public void notifyHealthObservers(String attackerName, String actionDetails, int currentHp) {
        for (HealthObserver observer : healthObservers) {
            observer.update(attackerName, actionDetails, currentHp);
        }
    }
}
