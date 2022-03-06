package com.tedi.tictactoe.demo.web.rest;

import com.tedi.tictactoe.demo.exception.InvalidGameException;
import com.tedi.tictactoe.demo.exception.InvalidParamException;
import com.tedi.tictactoe.demo.exception.NotFoundException;
import com.tedi.tictactoe.demo.model.Game;
import com.tedi.tictactoe.demo.model.GamePlay;
import com.tedi.tictactoe.demo.model.Player;
import com.tedi.tictactoe.demo.service.GameService;
import com.tedi.tictactoe.demo.web.request.ConnectRequest;
import com.tedi.tictactoe.demo.web.request.GameStartRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody GameStartRequest request) {
        log.info("start game request: {}", request.getPlayer());
        return ResponseEntity.ok(gameService.createGame(request.getPlayer(), request.getBoardSize()));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws NotFoundException {
        log.info("connect random {}", player);
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        Game game = gameService.gameplay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
        return ResponseEntity.ok(game);
    }
}
