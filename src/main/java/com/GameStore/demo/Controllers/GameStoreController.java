package com.GameStore.demo.Controllers;

import com.GameStore.demo.Games.Game;
import com.GameStore.demo.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/retrogames")
public class GameStoreController {

    @Autowired
   private GameService gameService;



    @GetMapping("/list-games")
    public String listGames(Model model,
                            @RequestParam(required = false) String search,
                            @RequestParam(required = false) String esrb) {
        List<Game> games;

        if (search != null && !search.isEmpty() && esrb != null && !esrb.isEmpty()) {
            // If both search and ESRB are provided, filter by both
            games = gameService.findByEsrbRatingAndTitle(esrb, search);
        } else if (search != null && !search.isEmpty()) {
            // If only search is provided, filter by title
            games = gameService.findByTitleContaining(search);
        } else if (esrb != null && !esrb.isEmpty()) {
            // If only ESRB is provided, filter by ESRB rating
            games = gameService.findByEsrbRating(esrb);
        } else {
            // If no filters are applied, return all games
            games = gameService.findAll();
        }


        model.addAttribute("games", games);
        model.addAttribute("search", search);
        model.addAttribute("esrb", esrb);

        return "list-games";
    }

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable("id") long theId, Model model){

        Game theGame = gameService.findById(theId);

        if (theGame == null){
            System.out.println("Game was not found");
        }

        model.addAttribute("game", theGame);

        return "game";
    }


    //Add Game Form GET
   @GetMapping("/addGameForm")
    public String createGameForm(Model model) {
       Game game = new Game();

       model.addAttribute("game", game);
       return "addGameForm";
   }

   //Add Game FORM POST
   @PostMapping("/addGameForm")
    public String saveGame(@ModelAttribute("game") Game game) {

        if (game.getPicture_url() != null && game.getPicture_url().isEmpty()) {
            game.setPicture_url(null);
        }

        gameService.save(game);
        return "redirect:/retrogames/list-games";
   }

   //delete game
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long theId) {
        gameService.deleteById(theId);
        return "redirect:/retrogames/list-games";
    }

  //Update Game
  @GetMapping("/edit/{id}")
  public String updateGameForm(@PathVariable("id") long gameId, Model model) {

      Game theGame = gameService.findById(gameId);

      if (theGame == null) {
          throw new RuntimeException("Game not found with ID: " + gameId);
      }

      model.addAttribute("game", theGame);
      return "editGameForm";
  }

    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable("id") long gameId, @ModelAttribute("game") Game updatedGame) {
        updatedGame.setId(gameId);
        gameService.save(updatedGame);
        return "redirect:/retrogames/list-games";
    }

    @GetMapping("/error/{id}")
    public String error(@PathVariable("id") long gameId, Model model){
        System.out.printf("Could not find game with id of %d ", gameId);
        model.addAttribute("gameId",gameId);
        return "error";
    }


}

