package game.moves.validation.checkers;

import communication.constants.pieces.CheckerType;
import game.pieces.GamePiece;

import java.awt.*;

public class CheckerDisplacementValidator {

    public boolean isValidDisplacement(GamePiece piece, Point finalLocation) throws Exception {
        Enum<?> type = piece.getPieceType();
        Point startingLocation = piece.getCell();
        if(type.equals(CheckerType.BASE)) {
            return verifyBaseCheckerDisplacement(startingLocation, finalLocation);
        } else if(type.equals(CheckerType.KING)) {
            return verifyKingCheckerDisplacement(startingLocation, finalLocation);
        }
        throw new Exception("Invalid Checker type supplied: " + type);
    }

    private boolean verifyBaseCheckerDisplacement(Point startingLocation, Point finalDestination) {
        Point displacement = getDisplacementFor(startingLocation, finalDestination);
        if(displacement.y <= 0) return false;
        return displacement.x != 3;
    }

    private boolean verifyKingCheckerDisplacement(Point startingLocation, Point finalDestination) {
        return true;
    }
    private Point getDisplacementFor(Point startingLocation, Point finalDestination) {
        int dx = finalDestination.x - startingLocation.x;
        int dy = finalDestination.y - startingLocation.y;
        return new Point(dx, dy);
    }
}
