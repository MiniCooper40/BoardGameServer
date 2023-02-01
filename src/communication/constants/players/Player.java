package communication.constants.players;

public enum Player {
    ONE,
    TWO,
    THREE,
    FOUR,
    UNDEFINED;

    public static Player fromNumber(int n) {
        return switch (n) {
            case 1 -> ONE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            default -> UNDEFINED;
        };
    }
}
