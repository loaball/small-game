package com.example.small.game.live.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SquareFieldNeighbourTest {

    @Test
    void testNeighbourCounterPoint00() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE}
        });

        Assertions.assertThat(testField.maxXNeighbourLocation(0)).isEqualTo(1);
        Assertions.assertThat(testField.minXNeighbourLocation(0)).isEqualTo(0);

        Assertions.assertThat(testField.maxYNeighbourLocation(0)).isEqualTo(1);
        Assertions.assertThat(testField.minYNeighbourLocation(0)).isEqualTo(0);

    }

    @Test
    void testNeighbourCounterPoint11() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE}
        });

        Assertions.assertThat(testField.maxXNeighbourLocation(1)).isEqualTo(2);
        Assertions.assertThat(testField.minXNeighbourLocation(1)).isEqualTo(0);

        Assertions.assertThat(testField.maxYNeighbourLocation(1)).isEqualTo(2);
        Assertions.assertThat(testField.minYNeighbourLocation(1)).isEqualTo(0);

    }

    @Test
    void testNeighbourCounterPoint22() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE}
        });

        Assertions.assertThat(testField.maxXNeighbourLocation(2)).isEqualTo(2);
        Assertions.assertThat(testField.minXNeighbourLocation(2)).isEqualTo(1);

        Assertions.assertThat(testField.maxYNeighbourLocation(2)).isEqualTo(2);
        Assertions.assertThat(testField.minYNeighbourLocation(2)).isEqualTo(1);

    }

    @Test
    void testAliveCounterWithFullAliveField() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE}
        });

        Assertions.assertThat(testField.countOfNeighbors(0, 0)).isEqualTo(3);
        Assertions.assertThat(testField.countOfNeighbors(2, 2)).isEqualTo(3);

        Assertions.assertThat(testField.countOfNeighbors(1, 1)).isEqualTo(8);
        Assertions.assertThat(testField.countOfNeighbors(0, 1)).isEqualTo(5);
    }

    @Test
    void testAliveCounterWithPartiallyDeadField() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.ALIVE, CellState.DEAD, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE},
                {CellState.ALIVE, CellState.ALIVE, CellState.ALIVE}
        });
        Assertions.assertThat(testField.countOfNeighbors(1, 1)).isEqualTo(7);
        Assertions.assertThat(testField.countOfNeighbors(0, 1)).isEqualTo(5);
        Assertions.assertThat(testField.countOfNeighbors(0, 0)).isEqualTo(2);
    }

    @Test
    void testAliveCounterWithPartiallyAliveField() {
        SquareField testField = new SquareField(new CellState[][]{
                {CellState.DEAD, CellState.ALIVE, CellState.DEAD, CellState.DEAD},
                {CellState.DEAD, CellState.DEAD, CellState.DEAD, CellState.DEAD},
                {CellState.DEAD, CellState.DEAD, CellState.DEAD, CellState.DEAD},
                {CellState.DEAD, CellState.DEAD, CellState.DEAD, CellState.DEAD}
        });
        Assertions.assertThat(testField.countOfNeighbors(2, 2)).isEqualTo(0);
        Assertions.assertThat(testField.countOfNeighbors(1, 1)).isEqualTo(1);
        Assertions.assertThat(testField.countOfNeighbors(0, 1)).isEqualTo(0);
    }

}
