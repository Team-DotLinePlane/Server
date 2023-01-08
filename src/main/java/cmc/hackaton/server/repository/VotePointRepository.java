package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Vote;
import cmc.hackaton.server.entity.VotePoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotePointRepository extends JpaRepository<VotePoint, Long> {
}
