package agh.ics.proj1;

public enum MapDir {
    N,
    NE,
    NW,
    S,
    SE,
    SW,
    W,
    E;

    public String toString() {
        return switch (this) {
            case N -> "^^";
            case NE -> "^>";
            case NW -> "<^";
            case S -> "vv";
            case SE -> "v>";
            case SW -> "<v";
            case W -> "<<";
            case E -> ">>";
        };
    }

    public MapDir next() {
        return switch (this) {
            case N -> NE;
            case NE -> E;
            case E -> SE;
            case SE -> S;
            case S -> SW;
            case SW -> W;
            case W -> NW;
            case NW -> N;
        };
    }

    public MapDir previous() {
        return switch (this) {
            case N -> NW;
            case NW -> W;
            case W -> SW;
            case SW -> S;
            case S -> SE;
            case SE -> E;
            case E -> NE;
            case NE -> N;
        };
    }

    public Vector2d toUnitVector() {
        Vector2d unit;
        return switch (this) {
            case N -> unit = new Vector2d(0,1);
            case NE -> unit = new Vector2d(1,1);
            case E -> unit = new Vector2d(1,0);
            case SE -> unit = new Vector2d(1,-1);
            case S -> unit = new Vector2d(0, -1);
            case SW -> unit = new Vector2d(-1,-1);
            case W -> unit = new Vector2d(-1,0);
            case NW -> unit = new Vector2d(-1,1);
        };
    }
}
