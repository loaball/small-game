package com.example.small.game.live.core;

public class SquareField implements Field {
    private Cell[][] field; // can be used Cell[] or List<Cell>

    public SquareField(CellState[][] initPattern) {
        //check that field has same detentions
        field = new Cell[initPattern.length][initPattern.length];
        initCells(initPattern);
    }

    private void initCells(CellState[][] initPattern) {
        for (int x = 0; x < initPattern.length; x++) {
            for (int y = 0; y < initPattern.length; y++) { //initPattern[x].length ...
                field[x][y] = new Cell(initPattern[x][y]);
            }
        }
    }

    //check that rule can not return null
    @Override
    public void foreach(StateChangeRule rule) {
        for (int x = 0; x < xSize(); x++) {
            for (int y = 0; y < ySize(); y++) {
                field[x][y].setState(rule.consider(countOfNeighbors(x, y), field[x][y].getState()));
            }
        }
    }

    @Override
    public CellState[][] currentState() {
        CellState[][] currentState = new CellState[xSize()][ySize()];
        for (int x = 0; x < xSize(); x++) {
            for (int y = 0; y < ySize(); y++) {
                currentState[x][y] = field[x][y].getState();
            }
        }
        return currentState;
    }

    // can be extracted to separate strategy which may return IntStream for example
    int countOfNeighbors(int x, int y) { // int countOfNeighbors(int x, int y, CellState withState) if we need to define which state are we interested in
        int minX = minXNeighbourLocation(x);
        int maxX = maxXNeighbourLocation(x);

        int minY = minYNeighbourLocation(y);
        int maxY = maxYNeighbourLocation(y);

        int counter = 0;

        for (int xc = minX; xc <= maxX; xc++) {
            for (int yc = minY; yc <= maxY; yc++) {
                if (xc == x && yc == y) {
                    continue; // skip current cell
                }

                if (field[xc][yc].getState() == CellState.ALIVE) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    //check x,y in range or throw IndexOutOfBoundsException
    int minXNeighbourLocation(int x) {
        return Math.max(0, x - 1);
    }

    int maxXNeighbourLocation(int x) {
        return Math.min(xSize() - 1, x + 1);
    }

    int minYNeighbourLocation(int y) {
        return Math.max(0, y - 1);
    }

    int maxYNeighbourLocation(int y) {
        return Math.min(ySize() - 1, y + 1);
    }

    CellState getState(int x, int y) {
        return field[x][y].getState();
    }

    private int xSize() {
        return field.length;
    }

    private int ySize() {
        return field.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = ySize() - 1; y >= 0; y--) {
            sb.append('\n');
            for (int x = 0; x < xSize(); x++) {
                sb.append(field[x][y]);
            }
        }
        return sb.toString();
    }
}
