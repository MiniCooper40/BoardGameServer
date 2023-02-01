package game.pieces.details;

import communication.constants.pieces.CheckerType;
import game.games.CheckersGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckerDetails implements GamePieceDetails {


    public static final long serialVersionUID = -2140850129444L;

    private static int ID_COUNT = 1;

    private final int id;
    private final GamePieceProperties pieceProperties;
    private final Point cell;

    public CheckerDetails(GamePieceProperties pieceProperties, Point cell) {
        this.id = ID_COUNT++;
        this.pieceProperties = pieceProperties;
        this.cell = cell;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Rectangle getPosition() {
        int screenX = cell.x * CheckersGame.CELL_LENGTH + CheckersGame.CHECKER_OFFSET;
        int screenY = cell.y * CheckersGame.CELL_LENGTH + CheckersGame.CHECKER_OFFSET;
        Point screenLocation = new Point(screenX, screenY);
        return new Rectangle(screenLocation, CheckersGame.CHECKER_DIMENSION);
    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    @Override
    public void moveTo(Point point) {
        cell.setLocation(point);
    }

    @Override
    public Enum<?> getPieceType() {
        return (Enum<?>) pieceProperties.getPieceType();
    }

    @Override
    public Point getCell() {
        return cell;
    }
}
