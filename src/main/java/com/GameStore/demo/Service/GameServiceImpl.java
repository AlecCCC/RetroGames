package com.GameStore.demo.Service;

import com.GameStore.demo.Games.Game;
import com.GameStore.demo.dao.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
//        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "esrb"));

        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Game findById(Long theId) {
        Optional<Game> result = gameRepository.findById(theId);

        Game theGame = null;

        if (result.isPresent()) {
            theGame = result.get();
        }
        else {
            // we didn't find the game
            throw new RuntimeException("Did not find game id - " + theId);
        }

        return theGame;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(long theId) {
        gameRepository.deleteById(theId);
    }


}
