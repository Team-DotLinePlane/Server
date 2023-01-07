package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByTeamCode(String teamCode);

    Optional<Team> findByTeamCode(String teamCode);
}
