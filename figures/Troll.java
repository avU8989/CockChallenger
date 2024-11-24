package worksheet5.figures;

import worksheet5.attack.BludgeonWeapon;
import worksheet5.attack.SlicingWeapon;
import worksheet5.exception.AttackException;
import worksheet5.exception.MissingWeaponException;
import worksheet5.figures.PlayerFigure;
import worksheet5.weapons.Club;
import worksheet5.weapons.Weapon;

import java.util.logging.Logger;

public class Troll extends PlayerFigure {

    public Troll(Weapon weapon) {
        super(weapon);
    }
}
