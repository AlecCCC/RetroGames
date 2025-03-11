package com.GameStore.demo.Service;

import com.GameStore.demo.Games.Game;

import java.util.List;

public interface GameService {

    List<Game> findAll(); // Get all games

    Game findById(long id); // Find a game by ID

    List<Game> filterGames(String title, String esrb); // Filtering method

    List<Game> findAllSortedByPrice(String sortOrder); // New method to get sorted games by price

    List<Game> findAllSortedByTitle();


    void save(Game game); // Save or update a game

    void deleteById(long id); // Delete a game
}
