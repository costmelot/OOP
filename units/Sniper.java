package units;

import java.util.List;

public class Sniper extends Shooter {
    public static final String HEROTYPE = "Снайпер";
    static final byte ATTACK = 12;
    static final byte DEFENCE = 10;
    static final byte HEALTH = 15;
    static final byte SPEED = 9;
    static final byte MINDAMAGE = 4;
    static final byte MAXDAMAGE = 6;
    static final byte SHOTS = 32;

    public Sniper(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, MINDAMAGE, MAXDAMAGE, SHOTS);
    }
}
