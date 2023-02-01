package game.state;

import communication.constants.pieces.CheckerType;
import communication.constants.players.Player;
import game.moves.validation.checkers.CheckersCellValidator;
import game.pieces.GameItems;
import game.pieces.GamePiece;
import game.pieces.checkers.Checker;
import game.pieces.details.CheckerDetails;
import game.pieces.details.GamePieceDetails;
import game.pieces.details.GamePieceProperties;

import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckersGameState extends GameState  {

    public CheckersGameState() {
        super(new CheckersCellValidator());

        GamePieceProperties defaultLightProperties = new GamePieceProperties(CheckerType.BASE, Player.ONE);
        GamePieceProperties defaultDarkProperties = new GamePieceProperties(CheckerType.BASE, Player.TWO);

        Set<GamePieceDetails> lightTeamDetails = Set.of(
            new CheckerDetails(defaultLightProperties, new Point(6, 7)),
            new CheckerDetails(defaultLightProperties, new Point(4, 7)),
            new CheckerDetails(defaultLightProperties, new Point(2, 7)),
            new CheckerDetails(defaultLightProperties, new Point(0, 7)),
            new CheckerDetails(defaultLightProperties, new Point(7, 6)),
            new CheckerDetails(defaultLightProperties, new Point(5, 6)),
            new CheckerDetails(defaultLightProperties, new Point(3, 6)),
            new CheckerDetails(defaultLightProperties, new Point(1, 6)),
            new CheckerDetails(defaultLightProperties, new Point(6, 5)),
            new CheckerDetails(defaultLightProperties, new Point(4, 5)),
            new CheckerDetails(defaultLightProperties, new Point(2, 5)),
            new CheckerDetails(defaultLightProperties, new Point(0, 5))
        );

        Set<GamePieceDetails> darkTeamDetails = Set.of(
                new CheckerDetails(defaultDarkProperties, new Point(6, 1)),
                new CheckerDetails(defaultDarkProperties, new Point(4, 1)),
                new CheckerDetails(defaultDarkProperties, new Point(2, 1)),
                new CheckerDetails(defaultDarkProperties, new Point(0, 1)),
                new CheckerDetails(defaultDarkProperties, new Point(7, 0)),
                new CheckerDetails(defaultDarkProperties, new Point(5, 0)),
                new CheckerDetails(defaultDarkProperties, new Point(3, 0)),
                new CheckerDetails(defaultDarkProperties, new Point(1, 0)),
                new CheckerDetails(defaultDarkProperties, new Point(7, 2)),
                new CheckerDetails(defaultDarkProperties, new Point(5,2)),
                new CheckerDetails(defaultDarkProperties, new Point(3, 2)),
                new CheckerDetails(defaultDarkProperties, new Point(1, 2))
        );

        Set<GamePiece> lightPieces = detailsToPieces(lightTeamDetails);
        Set<GamePiece> darkPieces = detailsToPieces(darkTeamDetails);

        Map<Player, Set<GamePiece>> gamePieces = Map.of(
                Player.ONE, lightPieces,
                Player.TWO, darkPieces
        );

        GameItems items = new GameItems();
        items.setActivePieces(gamePieces);

        setGameItems(items);
    }

    private Set<GamePiece> detailsToPieces(Set<GamePieceDetails> details) {
        return details
                .stream()
                .map(Checker::new)
                .collect(Collectors.toSet());
    }
}
