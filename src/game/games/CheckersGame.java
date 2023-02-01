package game.games;

import java.awt.*;

public class CheckersGame extends Game {

    public static final int SCREEN_LENGTH = 800;
    public static final int CELLS = 8;
    public static final int CELL_LENGTH = SCREEN_LENGTH/CELLS;

    public static final int CHECKER_DIAMETER = 80;
    public static final int CHECKER_OFFSET = 10;
    public static final Dimension CHECKER_DIMENSION = new Dimension(CHECKER_DIAMETER, CHECKER_DIAMETER);

}
