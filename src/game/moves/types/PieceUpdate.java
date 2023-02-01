package game.moves.types;

import game.moves.application.StateChangeApplier;
import game.moves.validation.StateChangeValidator;

public class PieceUpdate implements GameStateChange {
    @Override
    public boolean acceptValidation(StateChangeValidator validator) throws Exception {
        return validator.doValidation(this);
    }

    @Override
    public void acceptApplication(StateChangeApplier applier) throws Exception {
        applier.doApplication(this);
    }
}
