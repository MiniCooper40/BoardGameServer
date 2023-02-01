package communication.formats;

import communication.constants.network.RequestStatus;
import game.moves.types.GameStateChange;
import game.moves.types.NoChange;

import java.io.Serializable;

public class NetworkMessage implements Serializable {
    public static final long serialVersionUID = -1248091820498L;
    private final RequestStatus status;
    private final GameStateChange change;

    private NetworkMessage(RequestStatus status, GameStateChange change) {
        this.status = status;
        this.change = change;
    }

    public GameStateChange getChange() {
        return change;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public static class Builder {
        private RequestStatus status = RequestStatus.MADE_MOVE;
        private GameStateChange change = new NoChange();

        public Builder status(RequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder stateChange(GameStateChange change) {
            this.change = change;
            return this;
        }

        public NetworkMessage build() {
            return new NetworkMessage(status, change);
        }

    }

    public static NetworkMessage madeMove(GameStateChange change) {
        return new Builder()
                .status(RequestStatus.MADE_MOVE)
                .stateChange(change)
                .build();
    }

    @Override
    public String toString() {
        return "NetworkMessage{" +
                "status=" + status +
                ", change=" + change +
                '}';
    }
}
