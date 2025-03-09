package com.GameStore.demo.Controllers;

import com.GameStore.demo.Games.Game;
import com.GameStore.demo.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameStoreControllerREST {

    private GameService gameService;

    @Autowired
    public GameStoreControllerREST(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<Game> getAPI() {
     return gameService.findAll();
    }

    @GetMapping("/games/{gameId}")
    public Game getGame(@PathVariable Long gameId) {

        Game theGame = gameService.findById(gameId);

        if (theGame == null) {
            throw new RuntimeException("Employee id not found - " + gameId);
        }

        return theGame;
    }

}
