package com.example.small.game.live.core;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static com.example.small.game.live.core.CellState.ALIVE;
import static com.example.small.game.live.core.CellState.DEAD;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameIntegrationTest {
    private static final CellState[][] testInitPattern = new CellState[][]{
            {DEAD, DEAD, DEAD, DEAD, DEAD},
            {DEAD, ALIVE, DEAD, DEAD, DEAD},
            {DEAD, ALIVE, DEAD, ALIVE, DEAD},
            {DEAD, ALIVE, ALIVE, DEAD, DEAD},
            {DEAD, DEAD, DEAD, DEAD, DEAD}
    };
    private static Game testGame;

    @BeforeAll
    public static void setup() {
        testGame = new Game(testInitPattern);
    }

    @Order(1)
    @Test
    public void testCurrentStateTheSameAsInitPatternIfNoTick() {
        Assertions.assertThat(testGame.currentState()).isDeepEqualTo(testInitPattern);
    }

    @Order(2)
    @Test
    public void testFirstTick2CellDies() {
        testGame.tick();
        Assertions.assertThat(testGame.currentState()).isDeepEqualTo(new CellState[][]{
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD}
        });
    }

    @Order(3)
    @Test
    public void testSecondTick1CellBecomesAlive() {
        testGame.tick();
        Assertions.assertThat(testGame.currentState()).isDeepEqualTo(new CellState[][]{
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD}
        });
    }

    @Order(4)
    @Test
    public void testThirdTickNoChanges() {
        testGame.tick();
        Assertions.assertThat(testGame.currentState()).isDeepEqualTo(new CellState[][]{
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD, DEAD},
                {DEAD, ALIVE, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD, DEAD}
        });
    }

}
