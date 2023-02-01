package game.pieces.details;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public interface GamePieceDetails extends Serializable {
    int getID();
    Rectangle getPosition();
    BufferedImage getImage();
    void moveTo(Point point);

    Enum<?> getPieceType();

    default void moveTo(int x, int y) {
        moveTo(new Point(x, y));
    }
    Point getCell();
}
