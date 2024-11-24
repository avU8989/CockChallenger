package worksheet5;

import worksheet5.attack.BludgeonWeapon;
import worksheet5.attack.SlicingWeapon;
import worksheet5.combatlog.CombatLog;
import worksheet5.defense.DefenseStrategy;
import worksheet5.defense.Shield;

import worksheet5.exception.AttackException;
import worksheet5.figures.Knight;
import worksheet5.figures.Troll;
import worksheet5.healthtracker.HealthTracker;
import worksheet5.weapons.*;

import java.util.Random;

public class CardGameMain {

    public static void main (String[] args){
        DefenseStrategy shield = new Shield();


        Weapon sword = WeaponFactory.createSlicingWeapon(WeaponType.SWORD);
        Weapon club = WeaponFactory.createBludgeonWeapon(WeaponType.CLUB);

        Troll troll = new Troll(club);
        Knight knight = new Knight(sword);

        // Assign defense strategy
        knight.setDefenseStrategy(shield);

        // Add observers
        CombatLog combatLog = new CombatLog();
        HealthTracker healthTracker = new HealthTracker();

        knight.registerCombatObserver(combatLog);
        knight.registerHealthObserver(healthTracker);
        troll.registerCombatObserver(combatLog);
        troll.registerHealthObserver(healthTracker);

        Random random = new Random();
        boolean isTurn = random.nextBoolean();
        // Simulate combat
        try {
            if (isTurn) {
                while (true) {
                    knight.attack(troll);
                    troll.attack(knight);
                    System.out.println("----------------------------------------------------------");
                }
            } else {
                while (true) {
                    troll.attack(knight);
                    knight.attack(troll);
                    System.out.println("----------------------------------------------------------");
                }
            }
        }catch(AttackException e){
            System.exit(0);
        }



    }


}
