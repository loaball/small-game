package com.example.small.game.live;

import com.example.small.game.live.core.CellState;
import com.example.small.game.live.core.Game;
import lombok.NonNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GameConsoleAdapter {
    private final Game game;

    public GameConsoleAdapter(@NonNull String initField) {
        int[][] fieldWithCharsRepresentation = initFieldToChars(initField);
        CellState[][] initPattern = transform(fieldWithCharsRepresentation);
        game = new Game(initPattern);
    }


    public void startPresentation(@NonNull InputStream in, @NonNull OutputStream out) {
        try (Scanner reader = new Scanner(in);
             PrintWriter writer = new PrintWriter(out, true)) {
            do {
                print(writer);
                game.tick();
            } while (!inputRequest(reader, writer).equals("/q"));
        }
    }

    private String inputRequest(Scanner reader, PrintWriter writer) {
        writer.println("If you want to continue just press ENTER, if you want to quite type /q and press enter");
        return reader.nextLine();
    }

    private void print(PrintWriter writer) {
        writer.println(currentState());
    }

    // better rely on currentState() and build representation upon of it
    private String currentState() {
        return game.toString();
    }

    private int[][] initFieldToChars(String initField) {
        return initField.lines()
                .map(String::chars)
                .map(IntStream::toArray)
                .toArray(int[][]::new);
    }

    private CellState[][] transform(int[][] nativeField) {
        int detention = nativeField.length;
        CellState[][] initPattern = new CellState[detention][detention];
        for (int x = 0; x < detention; x++) {
            for (int y = 0; y < detention; y++) {
                initPattern[x][y] = CellState.fromChar(nativeField[detention - y - 1][x]);
            }
        }
        return initPattern;
    }
}
