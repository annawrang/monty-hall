package se.annawrang.montyhall.simulator;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SimulationStatistics {

    private int gamesPlayed = 0;
    private int winsWhenKeepingBox = 0;
    private int winsWhenChangingBox = 0;

    void incrementGamesPlayed() {
        gamesPlayed++;
    }

    void incrementWinsWhenKeepingBox() {
        winsWhenKeepingBox++;
    }

    void incrementWinsWhenChangingBox() {
        winsWhenChangingBox++;
    }
}
