package game.moves.types;


import game.moves.application.StateChangeApplier;
import game.moves.validation.StateChangeValidator;

public class NoChange implements GameStateChange {
    @Override
    public boolean acceptValidation(StateChangeValidator validator) throws Exception {
        return false;
    }

    @Override
    public void acceptApplication(StateChangeApplier applier) throws Exception {

    }
}
