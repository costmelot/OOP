package units;

import java.util.List;

public class Monk extends Supernatural {
    public static final String HEROTYPE = "Монах";
    static final byte ATTACK = 12;
    static final byte DEFENCE = 7;
    static final byte HEALTH = 30;
    static final byte SPEED = 5;
    static final byte DAMAGE = -4;
    static final byte MAGIC = 1;

    public Monk(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, DAMAGE, MAGIC);
    }
}
