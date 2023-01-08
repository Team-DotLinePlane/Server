package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByTeam_IdOrderByIdDesc(Long teamId);
}
