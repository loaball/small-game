package com.example.small.game.live.core;

public class Cell {
   private CellState state;
//   private List<Cell> neighbours; // if we do not want to recalculate neighbours each time

    public Cell(CellState cellState){
        this.state = cellState;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

//    public long getCountOfNeighbours() {
//        return neighbours.stream().map(Cell::getState).filter(ALIVE::equals).count();
//    }

    public String toString(){
        return state.getStateRepresentation();
    }
}
