package worksheet5;

import worksheet5.defense.Shield;
import worksheet5.defense.DefenseStrategy;
import worksheet5.combatlog.CombatLog;
import worksheet5.figures.*;
import worksheet5.healthtracker.HealthTracker;
import worksheet5.weapons.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TournamentMain {

    public static void main(String[] args) {
        // Create defense strategies
        DefenseStrategy shield = new Shield();

        // Create weapons
        Weapon sword = WeaponFactory.createSlicingWeapon(WeaponType.SWORD);
        Weapon club = WeaponFactory.createBludgeonWeapon(WeaponType.CLUB);
        Weapon dagger = WeaponFactory.createSlicingWeapon(WeaponType.DAGGER);
        Weapon staff = WeaponFactory.createMagicWeapon(WeaponType.MAGIC_WAND);

        // Create players
        Knight knight = new Knight(sword);
        knight.setDefenseStrategy(shield);

        Troll troll = new Troll(club);

        Assassin assassin = new Assassin(dagger);

        Wizard wizard = new Wizard(staff);

        // Add players to the tournament
        List<PlayerFigure> tournamentPlayers = new ArrayList<>();
        tournamentPlayers.add(knight);
        tournamentPlayers.add(troll);
        tournamentPlayers.add(assassin);
        tournamentPlayers.add(wizard);

        // Add observers
        CombatLog combatLog = new CombatLog();
        HealthTracker healthTracker = new HealthTracker();

        for (PlayerFigure player : tournamentPlayers) {
            player.registerCombatObserver(combatLog);
            player.registerHealthObserver(healthTracker);
        }
        Collections.shuffle(tournamentPlayers);
        // Simulate tournament
        System.out.println("Starting the tournament!\n");
        while (tournamentPlayers.size() > 1) {
            PlayerFigure player1 = tournamentPlayers.remove(0);
            PlayerFigure player2 = tournamentPlayers.remove(0);

            System.out.println("Match: " + player1.getClass().getSimpleName() + " vs. " + player2.getClass().getSimpleName());
            PlayerFigure winner = fight(player1, player2);

            System.out.println("Winner: " + winner.getClass().getSimpleName() + "\n");
            tournamentPlayers.add(winner);
        }

        System.out.println("The Champion is: " + tournamentPlayers.get(0).getClass().getSimpleName() + "!");
    }

    private static PlayerFigure fight(PlayerFigure player1, PlayerFigure player2) {
        Random random = new Random();
        boolean isTurn = random.nextBoolean();

        while (player1.isAlive() && player2.isAlive()) {
            if (isTurn) {
                player1.attack(player2);
            } else {
                player2.attack(player1);
            }
            isTurn = !isTurn;
            System.out.println("----------------------------------------------------------");
        }

        return player1.isAlive() ? player1 : player2;
    }
}
