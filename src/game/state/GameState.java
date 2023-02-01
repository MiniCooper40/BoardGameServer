package game.state;

import communication.constants.players.Player;
import game.moves.validation.CellValidator;
import game.pieces.GameItems;
import game.pieces.GamePiece;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GameState implements Serializable {

    private GameItems gameItems;
    private final CellValidator cellValidator;

    protected GameState(CellValidator cellValidator) {
        this.cellValidator = cellValidator;
        gameItems = new GameItems();
    }

    public void setGameItems(GameItems gameItems) {
        this.gameItems = gameItems;
    }

    public GameItems getGameItems() {
        return gameItems;
    }

    public Set<GamePiece> getGamePiecesFor(Player player) {
        return pieceMap()
                .get(player);
    }
    public Set<GamePiece> getMovableGamePiecesFor(Player player) {
        return getGamePiecesFor(player);
    }
    public Set<GamePiece> getAllGamePieces() {
        return pieceSet();
    }

    public Set<GamePiece> getAllDeletedGamePieces() {
        return Set.of();
    }

    public Set<GamePiece> getAllUnusedGamePieces() {
        return Set.of();
    }

    public boolean isValidCell(Point cell) {
        return cellValidator.isValidCell(cell);
    }

    public void deleteGamePiece(GamePiece piece) {
        int pieceID = piece.getID();
        deleteGamePiece(pieceID);
    }

    public void deleteGamePiece(int id) {
        GamePiece piece = getGamePieceByID(id);
        if(piece == null) throw new IllegalArgumentException("GamePiece with id=" + id + " is not active in this game");
        for(Map.Entry<Player, Set<GamePiece>> entry : pieceEntrySet()) {
            Set<GamePiece> playerPieces = entry.getValue();
            playerPieces.removeIf(p->p.getID()==id);
        }
    }
    public GamePiece getGamePieceByID(int id) {
        return usablePieceStream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .orElse(null);
    }
    public Player playerWithPieceWithID(int id) {
        GamePiece piece = getGamePieceByID(id);
        if(piece == null) return null;
        return pieceEntrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(piece))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
    public void removeGamePieceAt(Point cell) {
        for(Map.Entry<Player, Set<GamePiece>> entry : pieceEntrySet()) {
            Set<GamePiece> pieces = entry.getValue();
            pieces.removeIf(p -> p.getCell().equals(cell));
        }
    }
    public GamePiece getGamePieceAt(Point cell) {
        return usablePieceStream()
                .filter(p -> p.getCell().equals(cell))
                .findFirst()
                .orElse(null);
    }
    public boolean hasGamePieceAt(Point cell) {
        return getGamePieceAt(cell) != null;
    }

    private Map<Player, Set<GamePiece>> pieceMap() {
        return gameItems.getPieces();
    }

    private Stream<GamePiece> usablePieceStream() {
        return pieceMap()
                .values()
                .stream()
                .flatMap(Set::stream);
    }

    private Set<GamePiece> pieceSet() {
        return usablePieceStream()
                .collect(Collectors.toSet());
    }

    private Set<Map.Entry<Player, Set<GamePiece>>> pieceEntrySet() {
        return pieceMap()
                .entrySet();
    }
}
