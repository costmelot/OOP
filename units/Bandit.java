package units;

import java.util.List;

public class Bandit extends BaseHero {
    public static final String HEROTYPE = "Разбойник";
    static final byte ATTACK = 8;
    static final byte DEFENCE = 3;
    static final byte HEALTH = 10;
    static final byte SPEED = 6;
    static final byte MINDAMAGE = 2;
    static final byte MAXDAMAGE = 4;

    public Bandit(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, MINDAMAGE, MAXDAMAGE);
    }

    @Override
    public String toString() {
        return super.toString() + "\t  ";
    }
}