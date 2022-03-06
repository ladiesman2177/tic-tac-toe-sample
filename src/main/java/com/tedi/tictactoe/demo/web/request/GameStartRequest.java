package com.tedi.tictactoe.demo.web.request;

import com.tedi.tictactoe.demo.model.Player;

public class GameStartRequest {
    private Player player;
    private int boardSize;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
