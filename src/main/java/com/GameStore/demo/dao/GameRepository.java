package com.GameStore.demo.dao;

import com.GameStore.demo.Games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    // Filter by Title
    List<Game> findByTitleContainingIgnoreCase(String title);

    // Filter by ESRB rating
    List<Game> findByEsrbIgnoreCase(String esrb);

    // Search by title and ESRB rating
    List<Game> findByTitleContainingIgnoreCaseAndEsrbIgnoreCase(String title, String esrb);

    List<Game> findAllByOrderByPriceAsc();

    List<Game> findAllByOrderByTitleAsc();

}
