package com.example.cricketscore.repository;
import com.example.cricketscore.entity.BallEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BallEventRepository extends JpaRepository<BallEvent, Long> {
    List<BallEvent> findByInningsIdOrderByTimestampDesc(Long inningsId);
}
