package org.shamshad.models;

public class Move {
    private Player player;
    private Cell cell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getNewCell() {
        return cell;
    }

    public void setNewCell(Cell cell) {
        this.cell = cell;
    }
}
