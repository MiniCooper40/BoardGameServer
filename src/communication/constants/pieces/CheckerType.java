package communication.constants.pieces;

import java.awt.image.BufferedImage;

public enum CheckerType implements GamePieceType {

    BASE,
    KING;

    @Override
    public BufferedImage getIcon() {
        return null;
    }
}
