package game.moves.integration;

import game.moves.application.StateChangeApplier;
import game.moves.types.GameStateChange;
import game.moves.validation.StateChangeValidator;

public abstract class GameStateChangeIntegrator {

    private final StateChangeValidator validator;
    private final StateChangeApplier applier;

    protected GameStateChangeIntegrator(StateChangeValidator validator, StateChangeApplier applier) {
        this.validator = validator;
        this.applier = applier;
    }

    public void integrateStateChange(GameStateChange change) throws Exception {
        if(!validator.validate(change)) throw new Exception("Invalid move");
        applier.apply(change);
    }
}
