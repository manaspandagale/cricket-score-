package com.example.cricketscore.repository;
import com.example.cricketscore.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MatchRepository extends JpaRepository<Match, Long> {}
