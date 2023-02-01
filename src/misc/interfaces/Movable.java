package misc.interfaces;

import java.io.Serializable;

public interface Movable extends Clickable, Selectable {
    boolean canMove();
}
