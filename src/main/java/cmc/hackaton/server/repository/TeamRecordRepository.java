package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.record.TeamRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRecordRepository extends JpaRepository<TeamRecord, Long> {
    ;

    List<TeamRecord> findAllByTeam_Id(Long teamId);
}