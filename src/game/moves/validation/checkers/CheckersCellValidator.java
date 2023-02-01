package game.moves.validation.checkers;

import game.moves.validation.CellValidator;

import java.awt.*;

public class CheckersCellValidator implements CellValidator {

    @Override
    public boolean isValidCell(Point p) {
        return p.x <= 7 && p.x >=0 && p.y <= 7 && p.y >= 0;
    }
}
