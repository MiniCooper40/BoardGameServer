package game.moves.validation.checkers;

import communication.constants.players.Player;
import game.moves.types.PieceDeletion;
import game.moves.types.SimplePositionalMove;
import game.moves.validation.StateChangeValidator;
import game.pieces.GamePiece;
import game.state.GameState;

import java.awt.*;

public class CheckersStateChangeValidator implements StateChangeValidator {

    private GameState gameState;
    private final CheckerDisplacementValidator displacementValidator = new CheckerDisplacementValidator();

    public CheckersStateChangeValidator() {

    }

    public CheckersStateChangeValidator(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean doValidation(SimplePositionalMove move) throws Exception {
        System.out.println("Validating positional move in CSCV");
        return validateDisplacement(move)
                && validateDestination(move);
    }

    private boolean validateDisplacement(SimplePositionalMove move) throws Exception {
        int pieceID = move.getId();
        Point finalLocation = move.getFinalLocation();
        GamePiece piece = gameState.getGamePieceByID(pieceID);
        return displacementValidator.isValidDisplacement(piece, finalLocation);
    }

    private boolean validateDestination(SimplePositionalMove move) {
        Point finalLocation = move.getFinalLocation();
        GamePiece pieceAtFinalLocation = gameState.getGamePieceAt(finalLocation);
        return pieceAtFinalLocation == null
                || validatePieceAtDestination(move, pieceAtFinalLocation);
    }

    private boolean validatePieceAtDestination(SimplePositionalMove move, GamePiece pieceAtDestination) {
        int movedPieceID = move.getId();
        int pieceAtDestinationID = pieceAtDestination.getID();

        Player movedPieceOwner = gameState.playerWithPieceWithID(movedPieceID);
        Player destinationPieceOwner = gameState.playerWithPieceWithID(pieceAtDestinationID);

        return !movedPieceOwner.equals(destinationPieceOwner);
    }

    @Override
    public boolean doValidation(PieceDeletion deletion) throws Exception {
        System.out.println("Validating piece deletion move in CSCV");
        return true;
    }
}
