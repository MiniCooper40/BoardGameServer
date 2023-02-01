package game.moves.types;

import game.moves.application.StateChangeApplier;
import game.moves.validation.StateChangeValidator;
import game.pieces.GameItems;

public class AllGameItems implements GameStateChange {

    public static final long serialVersionUID = -124120959082059L;

    private final GameItems items;

    public AllGameItems(GameItems items) {
        this.items = items;
    }

    public GameItems getItems() {
        return items;
    }

    @Override
    public boolean acceptValidation(StateChangeValidator validator) {
        return true;
    }

    @Override
    public void acceptApplication(StateChangeApplier applier) throws Exception {
        applier.doApplication(this);
    }
}
