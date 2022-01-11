package com.example.small.game.live.core;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CellState {
    ALIVE("+"),
    DEAD("_");

    private final String stateRepresentation;
    private static final Map<String, CellState> mappings = Arrays.stream(CellState.values()).collect(Collectors.toMap(CellState::getStateRepresentation, Function.identity()));

    CellState(String stateRepresentation) {
        this.stateRepresentation = stateRepresentation;
    }

    public String getStateRepresentation() {
        return stateRepresentation;
    }

    public static CellState fromChar(int intChar) {
        String stateRepresentation = String.valueOf((char) intChar);
        return mappings.computeIfAbsent(stateRepresentation, key -> {
            throw new IllegalArgumentException("Key " + key + "is not allowed");
        });
    }
}
