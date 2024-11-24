package worksheet5.figures;

import worksheet5.attack.SlicingWeapon;
import worksheet5.exception.AttackException;
import worksheet5.exception.MissingWeaponException;
import worksheet5.figures.PlayerFigure;
import worksheet5.weapons.Sword;
import worksheet5.weapons.Weapon;

import java.util.Random;
import java.util.logging.Logger;

public class Knight extends PlayerFigure {

    public Knight(Weapon sword) {
        super(sword);
    }

}
