package units;

import java.util.List;
import java.util.Random;

public abstract class Supernatural extends BaseHero {

    private byte magic;
    private boolean isEmpty;

    public Supernatural(List<BaseHero> comrades, String heroType, Position position, byte attack, byte defence, byte health, byte speed, byte damage, byte magic) {
        super(comrades, heroType, position, attack, defence, health, speed, damage);
        this.magic = magic;
        this.isEmpty = false;
    }

    @Override
    public String toString() {
        return super.toString() + "\t🧪 " + this.magic;
    }

    @Override
    public byte[] getInfo() {
        byte[] info = super.getInfo();
        info[info.length - 1] = this.magic;
        return info;
    }


    @Override
    public void step(List<BaseHero> enemies) {
        if (this.isEmpty) {
            this.isEmpty = false;
            System.out.printf("%s %s пропускает ход\n", this.getHeroType(), this.getPosition().toString());
            return;
        }

        if (this.getHealth() > 0) {
            int worstHealth = 100;
            int heroHealth;
            BaseHero mostDamagedHero = null;
            for (BaseHero hero : this.getComrades()) {
                heroHealth = hero.getHealth();
                if (hero != this && heroHealth < worstHealth) {
                    worstHealth = heroHealth;
                    mostDamagedHero = hero;
                }
            }
            if (mostDamagedHero != null && worstHealth > 0) {
                System.out.printf("%s %s подлечил %s %s\n", this.getHeroType(), this.getPosition().toString(), mostDamagedHero.getHeroType(), mostDamagedHero.getPosition().toString());
                mostDamagedHero.setDamage(this.getInfo()[5]);
            }
            if (mostDamagedHero != null && worstHealth == 0) {
                String deadHeroType = mostDamagedHero.getHeroType();
                Position deadHeroPosition = mostDamagedHero.getPosition();
                int revivedHero = (new Random()).nextInt(TeamConstructor.GANG_SIZE);
                String revivedHeroType = this.getComrades().get(revivedHero).getHeroType();
                this.getComrades().remove(mostDamagedHero);
                TeamConstructor.createNewHero(revivedHeroType, this.getComrades(), deadHeroPosition);
                System.out.printf("%s %s воскресил %s %s\n", this.getHeroType(), this.getPosition().toString(), deadHeroType, deadHeroPosition);
                this.isEmpty = true;
            }
        }
    }
}
