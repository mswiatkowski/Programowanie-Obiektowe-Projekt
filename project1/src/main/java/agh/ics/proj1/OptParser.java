package agh.ics.proj1;

public class OptParser {

    public MapDir[] parse(Integer[] args) {
        MapDir[] mArray = new MapDir[args.length];
        int i = 0;
        for (Integer arg: args) {
            switch (arg) {
                case 1 -> {
                    mArray[i] = MapDir.NE;
                    i++;
                    break;
                }
                case 2 -> {
                    mArray[i] = MapDir.E;
                    i++;
                    break;
                }
                case 3 -> {
                    mArray[i] = MapDir.SE;
                    i++;
                    break;
                }
            }
        }
        return mArray;
    }

}
