package view;

import units.TeamConstructor;
import units.BaseHero;
import units.Position;

import java.util.Collections;
import java.util.List;

public class ConsoleView {

    private static int step = 1;

    private static final String top10 = formateDiv("a") + String.join("",
            Collections.nCopies(TeamConstructor.GANG_SIZE - 1, formateDiv("-b"))) + formateDiv("-c");
    private static final String mid10 = formateDiv("d") + String.join("",
            Collections.nCopies(TeamConstructor.GANG_SIZE - 1, formateDiv("-e"))) + formateDiv("-f");
    private static final String bott10 = formateDiv("g") + String.join("",
            Collections.nCopies(TeamConstructor.GANG_SIZE - 1, formateDiv("-h"))) + formateDiv("-i");


    public static void view(List<BaseHero> whiteSide, List<BaseHero> darkSide) {

        if (ConsoleView.step == 1) {
            System.out.println(AnsiColors.ANSI_GREEN + "First step" + AnsiColors.ANSI_RESET);
        } else {
            System.out.println("Step " + step + ".");
        }
        step++;

        System.out.println(ConsoleView.top10);

        for (byte y = 1; y <= TeamConstructor.GANG_SIZE; y++) {
            for (byte x = 1; x <= TeamConstructor.GANG_SIZE; x++) {
                System.out.print(getHeroChar(whiteSide, darkSide, new Position(x, y)));
            }
            System.out.print("|");
            System.out.println(getInfo(whiteSide, darkSide, y));
            System.out.println((y < TeamConstructor.GANG_SIZE) ? ConsoleView.mid10 : ConsoleView.bott10);
        }
        System.out.println("Press Enter");
        System.out.println();
    }

    private static String formateDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }

    private static String getHeroChar(List<BaseHero> whiteSide, List<BaseHero> darkSide, Position position) {
        String str = "| ";
        String whiteSideColor, darkSideColor;
        for (int i = 0; i < TeamConstructor.GANG_SIZE; i++) {
            whiteSideColor = whiteSide.get(i).getHealth() > 0 ? AnsiColors.ANSI_GREEN : AnsiColors.ANSI_RED;
            darkSideColor = darkSide.get(i).getHealth() > 0 ? AnsiColors.ANSI_BLUE : AnsiColors.ANSI_RED;
            if (darkSide.get(i).getPosition().isEqual(position)) {
                str = "|" + darkSideColor + darkSide.get(i).getHeroType().charAt(0) + AnsiColors.ANSI_RESET;
            }

            if (whiteSide.get(i).getPosition().isEqual(position)) {
                str = "|" + whiteSideColor + whiteSide.get(i).getHeroType().charAt(0) + AnsiColors.ANSI_RESET;
            }
        }
        return str;
    }

    private static String getInfo(List<BaseHero> whiteSide, List<BaseHero> darkSide, byte numberOfHero) {
        byte index = (byte) (numberOfHero - 1);
        String whiteSideColor, darkSideColor;
        whiteSideColor = whiteSide.get(index).getHealth() > 0 ? AnsiColors.ANSI_GREEN : AnsiColors.ANSI_RED;
        darkSideColor = darkSide.get(index).getHealth() > 0 ? AnsiColors.ANSI_BLUE : AnsiColors.ANSI_RED;
        return "\t" + (whiteSideColor) + whiteSide.get(index).toString() + AnsiColors.ANSI_RESET
                + "\t" + (darkSideColor) + darkSide.get(index).toString() + AnsiColors.ANSI_RESET;
    }

}
