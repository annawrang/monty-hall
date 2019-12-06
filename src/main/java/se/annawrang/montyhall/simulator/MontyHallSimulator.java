package se.annawrang.montyhall.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.annawrang.montyhall.game.Box;
import se.annawrang.montyhall.game.Game;

@Component
public class MontyHallSimulator {

    private final SimulationStatistics simulationStatistics;

    @Autowired
    MontyHallSimulator(SimulationStatistics simulationStatistics) {
        this.simulationStatistics = simulationStatistics;
    }

    public void simulateRounds(int rounds) {
        for (int i = 0; i < rounds; i++) {
            simulateGame();
        }
        printResult(rounds);
    }

    void simulateGame() {
        Game game = new Game();

        Box chosenBox = game.choseRandomBox();
        Box openBox = game.openEmptyBox();
        Box otherBox = game.getOtherBox();

        if (chosenBox.isWinner()) {
            simulationStatistics.incrementWinsWhenKeepingBox();
        } else if (otherBox.isWinner()) {
            simulationStatistics.incrementWinsWhenChangingBox();
        }
        simulationStatistics.incrementGamesPlayed();
    }

    private void printResult(int rounds) {
        System.out.println(String.format("\nRounds: %d\n" +
                        "Number of wins when changing box: %d\n" +
                        "Number of wins when keeping box : %d\n",
                rounds, simulationStatistics.getWinsWhenChangingBox(), simulationStatistics.getWinsWhenKeepingBox()));
    }

}
