package com.GameStore.demo.dao;

import com.GameStore.demo.Games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
