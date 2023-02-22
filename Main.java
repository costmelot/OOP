import units.*;
import view.ConsoleView;

import java.util.Collections;
//import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] whiteTeamMembers = {Sniper.HEROTYPE, Bandit.HEROTYPE, Peasant.HEROTYPE, Monk.HEROTYPE};
        String[] darkTeamMembers = {Crossbowman.HEROTYPE, Spearman.HEROTYPE, Peasant.HEROTYPE, Wisard.HEROTYPE};
        var whiteSide = TeamConstructor.createTeam(whiteTeamMembers);
        var darkSide = TeamConstructor.createTeam(darkTeamMembers);
        var sequenceOfSteps = TeamConstructor.rankTheHeroes(whiteSide, darkSide);

        Scanner scanner = new Scanner(System.in);

        while (TeamConstructor.isTeamAlive(whiteSide) && TeamConstructor.isTeamAlive(darkSide)) {

            for (var speed : sequenceOfSteps.descendingKeySet()) {
                Collections.shuffle(sequenceOfSteps.get(speed));
                for (var hero : sequenceOfSteps.get(speed)) {
                    if (hero.getComrades().equals(whiteSide)) {
                        hero.step(darkSide);
                    } else {
                        hero.step(whiteSide);
                    }
                }
            }
            ConsoleView.view(whiteSide, darkSide);
            scanner.nextLine();
            sequenceOfSteps.clear();
            sequenceOfSteps = TeamConstructor.rankTheHeroes(whiteSide, darkSide);
        }
    }
}
