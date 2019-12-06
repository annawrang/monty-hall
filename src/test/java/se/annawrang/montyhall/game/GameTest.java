package se.annawrang.montyhall.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.annawrang.montyhall.exception.BoxException;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {

    @Test
    @DisplayName("A game should have a list of 3 boxes with correct values")
    void shouldHaveThreeBoxes() {
        Game game = new Game();

        assertThat(game.getBoxes()).isNotNull();
        assertThat(game.getBoxes().size()).isEqualTo(3);
        assertThat(game.getBoxes().stream()
                .filter(Box::isWinner)
                .collect(Collectors.toList()).size())
                .as("One box should be the winner")
                .isEqualTo(1);
        assertThat(game.getBoxes().stream()
                .filter(Box::isOpen)
                .collect(Collectors.toList()).size())
                .as("No boxes should be open")
                .isEqualTo(0);
        assertThat(game.getBoxes().stream()
                .filter(Box::isChosen)
                .collect(Collectors.toList()).size())
                .as("No boxes should be chosen")
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Should choose a random box")
    void shouldSetBoxToChosen() {
        Game game = new Game();

        Box chosenBox = game.choseRandomBox();

        assertThat(chosenBox).isNotNull();
        assertThat(chosenBox.isChosen()).isTrue();
    }

    @Test
    @DisplayName("Should throw exception if there are no boxes that are not chosen")
    void shouldThrowException_noBoxes() {
        Game game = new Game();

        game.choseRandomBox();
        game.choseRandomBox();
        game.choseRandomBox();

        assertThatThrownBy(game::choseRandomBox)
                .isInstanceOf(BoxException.class);
    }

    @Test
    @DisplayName("Should throw exception if there are no losing boxes left to open")
    void shouldThrowException_noUnopenedNotChosenLosingBoxes() {
        Game game = new Game();

        game.openEmptyBox();
        game.openEmptyBox();

        assertThatThrownBy(game::openEmptyBox)
                .isInstanceOf(BoxException.class);
    }

    @Test
    @DisplayName("Should open a not chosen, closed, losing box")
    void shouldSetBoxToOpen() {
        Game game = new Game();

        Box openBox = game.openEmptyBox();

        assertThat(openBox).isNotNull();
        assertThat(openBox.isOpen()).isTrue();
        assertThat(openBox.isChosen()).isFalse();
        assertThat(openBox.isWinner()).isFalse();
    }

    @Test
    @DisplayName("Should throw exception if there are no boxes left to choose")
    void shouldThrowException_noClosedNotChosenBoxes() {
        Game game = new Game();

        game.choseRandomBox();
        game.choseRandomBox();
        game.choseRandomBox();

        assertThatThrownBy(game::choseRandomBox)
                .isInstanceOf(BoxException.class);
    }

    @Test
    @DisplayName("Should return a closed, not chosen box")
    void shouldReturnClosedNotChosenBox() {
        Game game = new Game();

        Box otherBox = game.getOtherBox();

        assertThat(otherBox).isNotNull();
        assertThat(otherBox.isChosen()).isFalse();
        assertThat(otherBox.isOpen()).isFalse();
    }
}
