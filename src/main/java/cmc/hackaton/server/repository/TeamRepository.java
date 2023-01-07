package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Team;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByTeamCode(String teamCode);

    Optional<Team> findByTeamCode(String teamCode);

    @Query(value = "SELECT *"
        + "         FROM team"
        + "         WHERE is_alarm_active"
        + "         AND DATE_FORMAT(meal_time, '%Y%m%d-%h:%i') = DATE_FORMAT(:now, '%Y%m%d-%h:%i')"
    , nativeQuery = true)
    List<Team> findTeamsByMealTime(@Param("now") LocalDateTime now);
}
