package units;

import java.util.List;


public class Peasant extends BaseHero {
    public static final String HEROTYPE = "Крестьянин";
    static final byte ATTACK = 1;
    static final byte DEFENCE = 1;
    static final byte HEALTH = 1;
    static final byte SPEED = 3;
    static final byte DAMAGE = 1;
    static final byte DELIVERY = 1;

    private byte delivery;

    private Peasant(List<BaseHero> comrades, String heroType, Position position, byte attack, byte defence, byte health, byte speed, byte damage, byte delivery) {
        super(comrades, heroType, position, attack, defence, health, speed, damage);
        this.delivery = delivery;
    }

    public Peasant(List<BaseHero> comrades, Position position) {
        this(comrades, HEROTYPE, position, ATTACK, DEFENCE, HEALTH, SPEED, DAMAGE, DELIVERY);
    }

    @Override
    public String toString() {
        return super.toString() + "\t 🚲" + this.delivery;
    }

    @Override
    public byte[] getInfo() {
        byte[] info = super.getInfo();
        info[info.length - 1] = this.delivery;
        return info;
    }

    @Override
    public void step(List<BaseHero> enemies) {
        if (this.getHealth() > 0) {
            for (BaseHero hero : this.getComrades()) {
                if ((hero.getHeroType().equals("Арбалетчик") || hero.getHeroType().equals("Снайпер")) && hero.setItem()) {
                    System.out.printf("Крестьянин %s доставил стрелу %s %s\n", this.getPosition().toString(), hero.getHeroType(), hero.getPosition().toString());
                    break;
                }
            }
        }
    }
}