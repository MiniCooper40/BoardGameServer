package game.moves.types;

import game.moves.application.StateChangeApplier;
import game.moves.validation.StateChangeValidator;

import java.io.Serializable;

public interface GameStateChange extends Serializable {
    boolean acceptValidation(StateChangeValidator validator) throws Exception;
    void acceptApplication(StateChangeApplier applier) throws Exception;
}
