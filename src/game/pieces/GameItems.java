package game.pieces;

import communication.constants.players.Player;
import game.pieces.details.GameConsumableType;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameItems implements Serializable {

    public static final long serialVersionUID = 1298401928412L;
    private Map<Player, Set<GamePiece>>  activePieces;
    private Map<GameConsumableType, GameConsumable> activeConsumables;

    public GameItems() {
        activePieces = new EnumMap<>(Player.class);
        activeConsumables = new HashMap<>();
    }

    public void setActivePieces(Map<Player, Set<GamePiece>> activePieces) {
        this.activePieces = activePieces;
    }

    public void setActiveConsumables(Map<GameConsumableType, GameConsumable> activeConsumables) {
        this.activeConsumables = activeConsumables;
    }

    private Map<Player, Set<GamePiece>> removedPieces;
    private Map<GameConsumableType, GameConsumable> usedConsumables;

    public Map<Player, Set<GamePiece>> getPieces() {
        return activePieces;
    }

    public Map<GameConsumableType, GameConsumable> getConsumables() {
        return activeConsumables;
    }
}
