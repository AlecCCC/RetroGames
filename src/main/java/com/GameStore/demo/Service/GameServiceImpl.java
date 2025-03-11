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

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));

//        return gameRepository.findAll();
    }

    @Override
    public Game findById(long id) {
        Optional<Game> result = gameRepository.findById(id);
        return result.orElse(null);
    }


    @Override
    public List<Game> filterGames(String title, String esrb) {
        if (title != null && !title.isEmpty() && esrb != null && !esrb.isEmpty()) {
            return gameRepository.findByTitleContainingIgnoreCaseAndEsrbIgnoreCase(title, esrb);

        } else if (title != null && !title.isEmpty()) {
            return gameRepository.findByTitleContainingIgnoreCase(title);

        } else if (esrb != null && !esrb.isEmpty()) {
            return gameRepository.findByEsrbIgnoreCase(esrb);

        } else {
            return gameRepository.findAll();
        }
    }

    @Override
    public List<Game> findAllSortedByPrice(String sortOrder) {
        if ("desc".equalsIgnoreCase(sortOrder)) {
            return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        } else {
            return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        }
    }

    @Override
    public List<Game> findAllSortedByTitle() {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void deleteById(long id) {
        gameRepository.deleteById(id);
    }
}
