package units;

import java.util.List;


public class Spearman extends BaseHero {
    public static final String HEROTYPE = "Пикинер";
    static final byte ATTACK = 4;
    static final byte DEFENCE = 5;
    static final byte HEALTH = 10;
    static final byte SPEED = 4;
    static final byte MINDAMAGE = 1;
    static final byte MAXDAMAGE = 3;

    public Spearman(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, MINDAMAGE, MAXDAMAGE);
    }
}
