package org.shamshad.strategies.botPlayingStrategies;

import org.shamshad.models.Board;
import org.shamshad.models.Cell;
import org.shamshad.models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row: board.getBoard()){
            for(Cell cell: row){
                if(cell.getCellState().equals(CellState.FREE)){
                    return cell;
                }
            }
        }
        return null;
    }
}
