package game.moves.types;

import game.moves.application.StateChangeApplier;
import game.moves.validation.StateChangeValidator;
import game.pieces.GamePiece;
import game.pieces.details.GamePieceDetails;

import java.awt.*;

public class SimplePositionalMove implements GameStateChange {

    private final int id;
    private final Point finalLocation;

    private SimplePositionalMove(int id, Point finalLocation) {
        this.id = id;
        this.finalLocation = finalLocation;
    }

    @Override
    public boolean acceptValidation(StateChangeValidator validator) throws Exception {
        return validator.doValidation(this);
    }

    @Override
    public void acceptApplication(StateChangeApplier applier) throws Exception {
        applier.doApplication(this);
    }

    public static class Builder {
        private int id;

        public Builder forPiece(GamePieceDetails details) {
            id = details.getID();
            return this;
        }

        public Builder forPiece(int id) {
            this.id = id;
            return this;
        }

        public Builder forPiece(GamePiece piece) {
            this.id = piece.getID();
            return this;
        }

        public SimplePositionalMove toLocation(Point finalLocation) {
            return new SimplePositionalMove(id, finalLocation);
        }
    }

    public int getId() {
        return id;
    }

    public Point getFinalLocation() {
        return finalLocation;
    }
}
