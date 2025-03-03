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
    public String listGames(Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        return "list-games";
    }

    //Home Page
    @GetMapping("/home")
    public String homePage(){

        return "home";
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
        return "redirect:/retrogames/home";
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
      return "addGameForm";
  }

    @PostMapping("/update/{id}")
    public String updateGame(@PathVariable("id") long gameId, @ModelAttribute("game") Game updatedGame) {
        updatedGame.setId(gameId);
        gameService.save(updatedGame);
        return "redirect:/retrogames/list-games";
    }


}

