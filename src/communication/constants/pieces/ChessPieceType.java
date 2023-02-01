package communication.constants.pieces;

import java.awt.image.BufferedImage;

public enum ChessPieceType implements GamePieceType {
    PAWN,
    BISHOP,
    KING,
    QUEEN,
    KNIGHT,
    ROOK;

    @Override
    public BufferedImage getIcon() {
        return null;
    }
}
