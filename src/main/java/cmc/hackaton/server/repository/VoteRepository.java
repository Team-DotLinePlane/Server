package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
