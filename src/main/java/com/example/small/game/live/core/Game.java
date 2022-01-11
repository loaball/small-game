package com.example.small.game.live.core;

// consider we do not need to know current state and toString is enough
public class Game {
    private int epoch = 0;
    private final Field field;

    public Game(CellState[][] initPattern) {
        // factory if field has different dimensions, or inject via constructor
        this.field = new SquareField(initPattern);
    }

    public void tick() {
        field.foreach(this::livenessRule);
        epoch += 1;
    }

    // can return a dedicated type here with it's own representation useful if we want do display on different UI
    public CellState[][] currentState() {
        return field.currentState();
    }

    //better to have it as a strategy
    CellState livenessRule(int countOfLiveNeighbours, CellState currentState) {
        if (countOfLiveNeighbours < 2 || countOfLiveNeighbours > 3) {
            return CellState.DEAD;
        }

        if (currentState == CellState.DEAD && countOfLiveNeighbours == 2) {
            return CellState.DEAD;
        }

        return CellState.ALIVE;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Current epoch: ")
                .append(epoch)
                .append(field)
                .toString();
    }
}
