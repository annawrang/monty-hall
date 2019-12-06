package se.annawrang.montyhall.game;

import lombok.Data;
import se.annawrang.montyhall.exception.BoxException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Game {

    private final List<Box> boxes;

    public Game() {
        boxes = createShuffledBoxes();
    }

    public Box choseRandomBox() {
        return boxes.stream()
                .filter(box -> !box.isChosen())
                .findAny()
                .map(box -> {
                    box.setChosen(true);
                    return box;
                }).orElseThrow(() -> new BoxException("No box available to choose"));
    }

    public Box openEmptyBox() {
        return boxes.stream()
                .filter(box -> !box.isChosen() && !box.isWinner() && !box.isOpen())
                .findAny()
                .map(box -> {
                    box.setOpen(true);
                    return box;
                }).orElseThrow(() -> new BoxException("No losing box left to open"));
    }

    public Box getOtherBox() {
        return boxes.stream()
                .filter(box -> !box.isChosen() && !box.isOpen())
                .findFirst()
                .orElseThrow(() -> new BoxException("No box left that is closed and not chosen"));
    }

    private List<Box> createShuffledBoxes() {
        List<Box> boxes = IntStream.range(0, 3)
                .boxed()
                .map(i -> Box.builder()
                        .chosen(false)
                        .open(false)
                        .winner(false)
                        .build())
                .collect(Collectors.toList());

        boxes.stream().findAny().ifPresent(box -> box.setWinner(true));

        Collections.shuffle(boxes);

        return boxes;
    }
}
