package se.annawrang.montyhall.game;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Box {

    private boolean winner;
    private boolean chosen;
    private boolean open;
}
