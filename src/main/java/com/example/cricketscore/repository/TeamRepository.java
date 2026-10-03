package com.example.cricketscore.repository;
import com.example.cricketscore.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TeamRepository extends JpaRepository<Team, Long> {}
