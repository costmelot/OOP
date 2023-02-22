package units;

import java.util.List;
import java.util.Random;


public abstract class BaseHero implements GameMethods {
    private List<BaseHero> comrades;
    private String heroType;
    private Position position;
    private byte attack, defence, health, maxHealth, speed, minDamage, maxDamage;

    public BaseHero(List<BaseHero> comrades, String heroType, Position position, byte attack, byte defence, byte health, byte speed, byte minDamage, byte maxDamage) {
        this.comrades = comrades;
        this.heroType = heroType;
        this.position = position;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public BaseHero(List<BaseHero> comrades, String heroType, Position position, byte attack, byte defence, byte health, byte speed, byte damage) {
        this(comrades, heroType, position, attack, defence, health, speed, damage, damage);
    }

    @Override
    public List<BaseHero> getComrades() {
        return this.comrades;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String getHeroType() {
        return this.heroType;
    }

    @Override
    public String toString() {
        String stringHero;
        stringHero = String.format("%-12s\t⚔ %d\t🛡 %d\t☠ %d\t♥% d\tV %d", this.heroType, this.attack, this.defence, (this.minDamage + this.maxDamage) / 2, this.health, this.speed);
        return stringHero;
    }

    @Override
    public byte[] getInfo() {
        byte[] info = {attack, defence, health, speed, minDamage, maxDamage, 0};

        return info;
    }

    @Override
    public int getHealth() {
        return 100 * this.health / this.maxHealth;
    }

    @Override
    public void setDamage(byte damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            System.out.printf("%s %s погиб.\n", this.getHeroType(), this.position.toString());
        }
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    @Override
    public boolean setItem() {
        return false;
    }

    @Override
    public void step(List<BaseHero> enemies) {
        if (this.health > 0) {
            BaseHero selectedEnemy = this.findNearestHero(enemies);
            if (this.health > 0 && selectedEnemy != null) {
                float distance = this.position.getDistance(selectedEnemy.getPosition());
                if (distance < 2) {
                    byte selectedEnemyDefence = selectedEnemy.getInfo()[1];
                    byte damage = (this.attack > selectedEnemyDefence) ? this.maxDamage : this.minDamage;
                    System.out.printf("%s %s атаковал %s %s\n", this.heroType, this.position.toString(), selectedEnemy.getHeroType(), selectedEnemy.getPosition().toString());
                    selectedEnemy.setDamage(damage);
                } else {
                    int dx = selectedEnemy.getPosition().x - this.position.x;
                    int dy = selectedEnemy.getPosition().y - this.position.y;
                    int stepX = (dx != 0) ? Math.round(dx / Math.abs(dx)) : 0;
                    int stepY = (dy != 0) ? Math.round(dy / Math.abs(dy)) : 0;
                    boolean isStepCompleted = false;
                    byte counter = 4;
                    while (!isStepCompleted && counter > 0) {
                        byte x = this.position.x;
                        byte y = this.position.y;
                        if (stepX != 0 && stepY != 0) {
                            switch ((new Random()).nextInt(2)) {
                                case 0:
                                    x = (byte) (x + stepX);
                                    break;
                                case 1:
                                    y = (byte) (y + stepY);
                                    break;
                            }
                        } else {
                            x = (byte) (x + stepX);
                            y = (byte) (y + stepY);
                        }
                        Position newPosition = new Position(x, y);
                        boolean isFieldBusy = false;
                        for (BaseHero hero : this.comrades) {
                            if (hero.getPosition().isEqual(newPosition)) {
                                isFieldBusy = true;
                                break;
                            }
                        }
                        for (BaseHero hero : enemies) {
                            if (isFieldBusy || hero.getPosition().isEqual(newPosition)) {
                                isFieldBusy = true;
                                break;
                            }
                        }
                        if (isFieldBusy || x < Position.MINPOSITION || x > Position.MAXPOSITION || y < Position.MINPOSITION || y > Position.MAXPOSITION) {
                            int swap = stepX;
                            stepX = stepY;
                            stepY = -swap;
                            counter--;
                        } else {
                            this.position = newPosition;
                            isStepCompleted = true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public BaseHero findNearestHero(List<BaseHero> heroes) {
        float minDistance = Position.MAXDISTANCE;
        float distance;
        BaseHero selectedHero = null;
        for (BaseHero hero : heroes) {
            distance = hero.getPosition().getDistance(this.position);
            if (hero != this && hero.getHealth() > 0 && distance < minDistance) {
                minDistance = distance;
                selectedHero = hero;
            }
        }
        return selectedHero;
    }
}