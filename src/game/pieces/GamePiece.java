package game.pieces;

import game.pieces.details.GamePieceDetails;
import misc.interfaces.Drawable;
import misc.interfaces.Selectable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class GamePiece implements Selectable, Drawable, Serializable {


    public static final long serialVersionUID = -87192847019029L;
    private GamePieceDetails pieceDetails;
    private boolean selected = false;

    public GamePiece(GamePieceDetails pieceDetails) {
        this.pieceDetails = pieceDetails;
    }

    @Override
    public void draw(Graphics g) {
        Rectangle position = pieceDetails.getPosition();
        BufferedImage image = pieceDetails.getImage();
        g.drawImage(image, position.x, position.y, position.width, position.height, null);
    }

    public boolean containsClickedPoint(Point point) {
        Rectangle position = pieceDetails.getPosition();
        return position.contains(point);
    }

    public int getID() {
        return pieceDetails.getID();
    }

    public Point getCell() {
        Rectangle position = pieceDetails.getPosition();
        return position.getLocation();
    }

    public void moveTo(Point cell) {
        pieceDetails.moveTo(cell);
    }

    public Enum<?> getPieceType() {
        return pieceDetails.getPieceType();
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void deselect() {
        selected = false;
    }
}
