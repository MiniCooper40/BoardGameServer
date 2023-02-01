package game.moves.integration;

import game.moves.application.CheckersStateChangeApplier;
import game.moves.validation.checkers.CheckersStateChangeValidator;
import game.state.GameState;

public class CheckersStateChangeIntegrator extends GameStateChangeIntegrator{
    public CheckersStateChangeIntegrator(GameState state) {
        super(new CheckersStateChangeValidator(state),
                new CheckersStateChangeApplier(state));
    }
}
