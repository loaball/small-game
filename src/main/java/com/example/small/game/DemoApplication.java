package com.example.small.game;

import com.example.small.game.live.GameConsoleAdapter;

public class DemoApplication {
    // text block if java 15+
    // '+' - alive, '_' - no
    private static String initField =
                    "_____\n" +
                    "__+__\n" +
                    "___+_\n" +
                    "_+++_\n" +
                    "_____\n";

    public static void main(String[] args) {
        GameConsoleAdapter consoleGame = new GameConsoleAdapter(initField);
        consoleGame.startPresentation(System.in, System.out);
    }

}
