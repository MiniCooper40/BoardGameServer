package game.moves.validation;

import game.moves.types.GameStateChange;
import game.moves.types.PieceDeletion;
import game.moves.types.PieceUpdate;
import game.moves.types.SimplePositionalMove;

public interface StateChangeValidator {

    default boolean validate(GameStateChange change) throws Exception {
        return change.acceptValidation(this);
    }
    default boolean doValidation(PieceDeletion deletion) throws Exception {
        throw new Exception("Piece deletion is not a valid operation for this game");
    }
    default boolean doValidation(SimplePositionalMove move) throws Exception {
        throw new Exception("Positional movement is not a valid operation for this game");
    }

    default boolean doValidation(PieceUpdate update) throws Exception {
        throw new Exception("PieceUpdate is not a valid operation for this game");
    }
}
