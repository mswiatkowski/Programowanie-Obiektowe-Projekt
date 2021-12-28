package agh.ics.proj1;

import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;
    public long id;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(")
                .append(this.x)
                .append(",")
                .append(this.y)
                .append(")");
        return builder.toString();
    }

    Vector2d add(Vector2d other) {
        int addx;
        int addy;
        addx = (this.x + other.x);
        addy = (this.y + other.y);
        return new Vector2d(addx,addy);
    }

    Vector2d subtract(Vector2d other) {
        int subtrx;
        int subtry;
        subtrx = (this.x - other.x);
        subtry = (this.y - other.y);
        return new Vector2d(subtrx,subtry);
    }

    @Override
    public boolean equals(Object other) {
        if (Objects.equals(other.toString(), this.toString())) {
            return true;
        }
        else {
            return false;
        }
    }
}
