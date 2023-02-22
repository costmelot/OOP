package units;

import java.util.List;


public class Wisard extends Supernatural {
    public static final String HEROTYPE = "Чародей";
    static final byte ATTACK = 17;
    static final byte DEFENCE = 12;
    static final byte HEALTH = 30;
    static final byte SPEED = 9;
    static final byte DAMAGE = -5;
    static final byte MAGIC = 1;

    public Wisard(List<BaseHero> comrades, Position position) {
        super(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, DAMAGE, MAGIC);
    }
}
