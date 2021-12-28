package agh.ics.proj1;

import java.util.LinkedList;
import java.util.List;

public class AbstractMap implements IMap {
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x >= 0) && (position.y >= 0)
                && (!isOccupied(position))
                && (position.x < 10) && (position.y < 10);
    }

    @Override
    public boolean place(Animal animal) {
        List<Boolean> booleanList = new LinkedList<>();
        for (Animal a: SimEngine.mapanimals.values()) {
            if (a.equals(animal)) {
                booleanList.add(true);
                break;
            }
        }
        return booleanList.contains(true);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return SimEngine.mapanimals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return SimEngine.mapanimals.getOrDefault(position, null);
    }
}
