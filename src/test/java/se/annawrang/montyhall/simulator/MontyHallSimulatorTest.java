package se.annawrang.montyhall.simulator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MontyHallSimulatorTest {

    @InjectMocks
    private MontyHallSimulator montyHallSimulator;

    @Mock
    private SimulationStatistics simulationStatistics;

    @Test
    @DisplayName("For every round that is played statistics should be recorded")
    void simulateGameShouldSetStatistics() {
        montyHallSimulator.simulateGame();

        Collection<Invocation> invocations = Mockito.mockingDetails(simulationStatistics).getInvocations();
        assertThat(invocations.size()).isEqualTo(2);

        verify(simulationStatistics, times(1)).incrementGamesPlayed();
        verify(simulationStatistics, Mockito.atMostOnce()).incrementWinsWhenChangingBox();
        verify(simulationStatistics, Mockito.atMostOnce()).incrementWinsWhenKeepingBox();

    }

    @Test
    @DisplayName("A certain number of rounds should be simulated")
    void simulateNumberOfRoundsShouldSetStatistics() {
        int numberOfRounds = 100;

        montyHallSimulator.simulateRounds(numberOfRounds);

        Collection<Invocation> invocations = Mockito.mockingDetails(simulationStatistics).getInvocations();

        verify(simulationStatistics, times(numberOfRounds)).incrementGamesPlayed();
        assertThat(invocations.size())
                .as("Statistics should have been called twice as many times as number of rounds"
                        + " to set gamesPlayed and winner + 2 times for printing result")
                .isEqualTo((numberOfRounds * 2) + 2);
    }
}