package game.pieces.details;

import communication.constants.pieces.GamePieceType;
import communication.constants.players.Player;

import java.io.Serializable;

public class GamePieceProperties implements Serializable {

    private GamePieceType pieceType;
    private Player owner;

    public GamePieceProperties(GamePieceType pieceType, Player owner) {
        this.pieceType = pieceType;
        this.owner = owner;
    }

    public GamePieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(GamePieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
