package game.moves.application;

import game.moves.types.*;

public interface StateChangeApplier {

    default void apply(GameStateChange change) throws Exception {
        change.acceptApplication(this);
    }
    default void doApplication(PieceDeletion deletion) throws Exception {
        throw new Exception("Piece deletion is not a valid operation for this game");
    }
    default void doApplication(SimplePositionalMove move) throws Exception {
        throw new Exception("Positional movement is not a valid operation for this game");
    }

    default void doApplication(PieceUpdate update) throws Exception {
        throw new Exception("PieceUpdate is not a valid operation for this game");
    }

    default void doApplication(AllGameItems update) throws Exception {
        throw new Exception("AllGameItems is not a valid operation for this game");
    }
}
