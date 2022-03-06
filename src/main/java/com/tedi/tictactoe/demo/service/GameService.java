package com.tedi.tictactoe.demo.service;

import com.tedi.tictactoe.demo.exception.InvalidGameException;
import com.tedi.tictactoe.demo.exception.InvalidParamException;
import com.tedi.tictactoe.demo.exception.NotFoundException;
import com.tedi.tictactoe.demo.model.Game;
import com.tedi.tictactoe.demo.model.GamePlay;
import com.tedi.tictactoe.demo.model.Player;
import com.tedi.tictactoe.demo.model.TicToe;

public interface GameService {
    Game createGame(Player player, int boardSize);
    Game connectToGame(Player player2, String gameId)throws InvalidParamException, InvalidGameException;
    Game connectToRandomGame(Player player2)throws NotFoundException;
    Game gameplay(GamePlay gamePlay) throws NotFoundException, InvalidGameException;
}
