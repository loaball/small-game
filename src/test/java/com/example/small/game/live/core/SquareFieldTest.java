package com.example.small.game.live.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.small.game.live.core.CellState.ALIVE;
import static com.example.small.game.live.core.CellState.DEAD;

@ExtendWith(MockitoExtension.class)
public class SquareFieldTest {

    private SquareField testField;

    @Mock
    private Field.StateChangeRule stateChangeRule;

    @Captor
    private ArgumentCaptor<Integer> neighbourCaptor;

    @BeforeEach
    public void init() {
        CellState[][] pattern = {{ALIVE, ALIVE, DEAD}, {DEAD, ALIVE, DEAD}, {DEAD, DEAD, DEAD}};
        testField = new SquareField(pattern);
    }

    @Test
    void testStateChangeRuleInvocation() {
        Mockito.when(stateChangeRule.consider(Mockito.anyInt(), Mockito.any(CellState.class))).thenAnswer(
                a -> a.getArguments()[1]);

        testField.foreach(stateChangeRule);

        Mockito.verify(stateChangeRule, Mockito.times(9)).consider(neighbourCaptor.capture(), Mockito.any(CellState.class));
        Assertions.assertThat(neighbourCaptor.getAllValues()).containsExactly(
                2, 2, 2,
                3, 2, 2,
                1, 1, 1
        );
    }

    @Test
    void testStateChangeRuleCanChangeStateOfTheField() {
        Mockito.when(stateChangeRule.consider(Mockito.anyInt(), Mockito.any(CellState.class))).thenReturn(ALIVE);

        testField.foreach(stateChangeRule);

        Mockito.verify(stateChangeRule, Mockito.times(9)).consider(neighbourCaptor.capture(), Mockito.any(CellState.class));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Assertions.assertThat(testField.getState(x, y)).isEqualTo(ALIVE);
            }
        }
    }
}
