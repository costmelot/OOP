package units;

import java.util.List;

public interface GameMethods {
    List<BaseHero> getComrades();

    Position getPosition();

    void setPosition(Position position);

    String getHeroType();

    byte[] getInfo();

    int getHealth();

    void setDamage(byte damage);

    boolean setItem();

    void step(List<BaseHero> enemies);

    BaseHero findNearestHero(List<BaseHero> heroes);
}