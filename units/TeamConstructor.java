package units;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;


public class TeamConstructor {
    public static final byte GANG_SIZE = 10;
    private static byte x;

    static {
        x = 1;
    }

    public static List<BaseHero> createTeam(String[] heroes) {
        List<BaseHero> team = new ArrayList<>();
        Random rnd = new Random();
        int num;
        for (byte y = 1; y <= GANG_SIZE; y++) {
            num = rnd.nextInt(heroes.length);
            createNewHero(heroes[num], team, new Position(x, y));
        }
        x *= GANG_SIZE;
        return team;
    }

    public static void createNewHero(String heroType, List<BaseHero> team, Position position) {
        if (Bandit.HEROTYPE.equals(heroType)) team.add(new Bandit(team, position));
        if (Crossbowman.HEROTYPE.equals(heroType)) team.add(new Crossbowman(team, position));
        if (Monk.HEROTYPE.equals(heroType)) team.add(new Monk(team, position));
        if (Peasant.HEROTYPE.equals(heroType)) team.add(new Peasant(team, position));
        if (Sniper.HEROTYPE.equals(heroType)) team.add(new Sniper(team, position));
        if (Spearman.HEROTYPE.equals(heroType)) team.add(new Spearman(team, position));
        if (Wisard.HEROTYPE.equals(heroType)) team.add(new Wisard(team, position));

    }

    public static boolean isTeamAlive(List<BaseHero> team) {
        boolean isAnybodyHereAlive = false;
        for (BaseHero hero : team) {
            if (hero.getHealth() > 0) {
                isAnybodyHereAlive = true;
                break;
            }
        }
        return isAnybodyHereAlive;
    }

    public static TreeMap<Byte, List<BaseHero>> rankTheHeroes(List<BaseHero> team1, List<BaseHero> team2) {
        TreeMap<Byte, List<BaseHero>> rangedHeroes = new TreeMap<>();
        for (BaseHero hero : team1) {
            addHeroInBattle(hero, rangedHeroes);
        }
        for (BaseHero hero : team2) {
            addHeroInBattle(hero, rangedHeroes);
        }
        return rangedHeroes;
    }

    private static void addHeroInBattle(BaseHero hero, TreeMap<Byte, List<BaseHero>> battle) {
        Byte speed = hero.getInfo()[3];
        if (!battle.containsKey(speed)) {
            battle.put(speed, new ArrayList<BaseHero>());
        }
        battle.get(speed).add(hero);
    }

}
