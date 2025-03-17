package com.GameStore.demo.Service;

import com.GameStore.demo.Games.Game;

import java.util.List;

public interface GameService {

    List<Game> findAll();

    Game findById(Long theId);

    Game save(Game game);

    void deleteById(long theId);

    List<Game> findByTitleContaining(String title);

    List<Game> findByEsrbRating(String esrb);

    List<Game> findByEsrbRatingAndTitle(String esrb, String title);

}
