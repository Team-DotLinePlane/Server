package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
