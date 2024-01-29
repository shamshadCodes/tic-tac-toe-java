package org.shamshad.model;

public class Move {
    private Player player;
    private Cell currentCell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.currentCell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getNewCell() {
        return currentCell;
    }

    public void setNewCell(Cell cell) {
        this.currentCell = cell;
    }
}
