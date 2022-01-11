package com.example.small.game.live.core;

public interface Field {

    void foreach(StateChangeRule rule);
    CellState[][] currentState();

    interface StateChangeRule {
        CellState consider(int countOfLiveNeighbours, CellState currentState);
    }
}
