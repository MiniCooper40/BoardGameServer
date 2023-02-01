package game.moves.application;

import game.moves.types.AllGameItems;
import game.moves.types.PieceDeletion;
import game.moves.types.SimplePositionalMove;
import game.pieces.GameItems;
import game.state.GameState;

public class CheckersStateChangeApplier implements StateChangeApplier {

    private final GameState gameState;

    public CheckersStateChangeApplier(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void doApplication(SimplePositionalMove move) throws Exception {
        System.out.println("Applying positional move in CSCA");
    }

    @Override
    public void doApplication(PieceDeletion deletion) throws Exception {
        System.out.println("Applying piece deletion move in CSCA");
    }

    @Override
    public void doApplication(AllGameItems update) throws Exception {
        GameItems newItems = update.getItems();
        gameState.setGameItems(newItems);
    }
}
