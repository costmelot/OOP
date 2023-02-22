package units;

import java.util.List;

public class Crossbowman extends Shooter {
    public static final String HEROTYPE = "Арбалетчик";
    static final byte ATTACK = 6;
    static final byte DEFENCE = 3;
    static final byte HEALTH = 10;
    static final byte SPEED = 4;
    static final byte MINDAMAGE = 2;
    static final byte MAXDAMAGE = 3;
    static final byte SHOTS = 16;

    public Crossbowman(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, MINDAMAGE, MAXDAMAGE, SHOTS);
    }
}
