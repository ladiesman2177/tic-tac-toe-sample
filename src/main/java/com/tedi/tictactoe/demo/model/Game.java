package com.tedi.tictactoe.demo.model;

import lombok.Data;

@Data
public class Game {
    private String gameId;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private int boardSize;
    private int[][] board;
    private TicToe winner;
    private int[][] winCombinations;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public TicToe getWinner() {
        return winner;
    }

    public void setWinner(TicToe winner) {
        this.winner = winner;
    }

    public int[][] getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(int[][] winCombinations) {
        this.winCombinations = winCombinations;
    }

}
