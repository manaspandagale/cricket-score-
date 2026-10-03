package com.example.cricketscore.repository;
import com.example.cricketscore.entity.PlayerInningsStats;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlayerInningsStatsRepository extends JpaRepository<PlayerInningsStats, Long> {
    Optional<PlayerInningsStats> findByInningsIdAndPlayerId(Long inningsId, Long playerId);
}
