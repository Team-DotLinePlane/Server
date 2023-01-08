package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByTeam_IdOrderByIdDesc(Long teamId);
}
