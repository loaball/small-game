package com.example.small.game.live.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.small.game.live.core.CellState.ALIVE;
import static com.example.small.game.live.core.CellState.DEAD;
import static org.junit.jupiter.params.provider.Arguments.of;

public class GameTest {

   private static Stream<Arguments> arguments = Stream.of(
            of(0, ALIVE, DEAD),
            of(1, ALIVE, DEAD),
            of(2, ALIVE, ALIVE),
            of(3, ALIVE, ALIVE),
            of(4, ALIVE, DEAD),
            of(5, ALIVE, DEAD),
            of(6, ALIVE, DEAD),
            of(7, ALIVE, DEAD),
            of(8, ALIVE, DEAD),

            of(0, DEAD, DEAD),
            of(1, DEAD, DEAD),
            of(2, DEAD, DEAD),
            of(3, DEAD, ALIVE),
            of(4, DEAD, DEAD),
            of(5, DEAD, DEAD),
            of(6, DEAD, DEAD),
            of(7, DEAD, DEAD),
            of(8, DEAD, DEAD)
    );

    private static Game testGame;

    @BeforeAll
    public static void init(){
        testGame = new Game(new CellState[][]{});
    }


    @ParameterizedTest
    @MethodSource("possibleLiveStates")
    public void testLivenessRule(int countOfLiveNeighbours, CellState currentState, CellState expectedState){
        Assertions.assertThat(testGame.livenessRule(countOfLiveNeighbours, currentState)).isEqualTo(expectedState);
    }

    private static Stream<Arguments> possibleLiveStates() {
        return arguments;
    }

}
