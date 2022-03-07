package com.tedi.tictactoe.demo.service.impl;

import com.tedi.tictactoe.demo.exception.InvalidGameException;
import com.tedi.tictactoe.demo.exception.InvalidParamException;
import com.tedi.tictactoe.demo.exception.NotFoundException;
import com.tedi.tictactoe.demo.model.*;
import com.tedi.tictactoe.demo.service.GameService;
import com.tedi.tictactoe.demo.storage.GameStorage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public Game createGame(Player player, int boardSize) {
        Game game = new Game();
        game.setBoard(new int[boardSize][boardSize]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setGameStatus(GameStatus.NEW);
        game.setBoardSize(boardSize);

        GameStorage.getInstance().setGame(game);
        return game;
    }

    @Override
    public Game connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    @Override
    public Game connectToRandomGame(Player player2) throws NotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(it -> it.getGameStatus().equals(GameStatus.NEW))
                .findFirst().orElseThrow(() -> new NotFoundException("Game not found"));
        game.setPlayer2(player2);
        game.setGameStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    @Override
    public Game gameplay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getGameStatus().equals(GameStatus.FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        Boolean xWinner = checkWinner(game.getBoard(), game.getBoardSize(), gamePlay.getCoordinateX(), gamePlay.getCoordinateY(),TicToe.X);
        Boolean oWinner = checkWinner(game.getBoard(), game.getBoardSize(), gamePlay.getCoordinateX(), gamePlay.getCoordinateY(),TicToe.O);

        if (xWinner) {
            game.setWinner(TicToe.X);
        } else if (oWinner) {
            game.setWinner(TicToe.O);
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }

    private Boolean checkWinner(int[][] board, int n, int x, int y, TicToe ticToe) {
        boolean result = false;
        for (int i = 0; i < n; i++) {
            if (board[x][i] != ticToe.getValue())
                break;
            if (i == n - 1) {
                result = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (board[i][y] != ticToe.getValue())
                break;
            if (i == n - 1) {
                result = true;
            }
        }

        if (x == y) {
            //we're on a diagonal
            for (int i = 0; i < n; i++) {
                if (board[i][i] != ticToe.getValue())
                    break;
                if (i == n - 1) {
                    result = true;
                }
            }
        }

        if (x + y == n - 1) {
            for (int i = 0; i < n; i++) {
                if (board[i][(n - 1) - i] != ticToe.getValue())
                    break;
                if (i == n - 1) {
                    result = true;
                }
            }
        }
        return result;
    }
}
