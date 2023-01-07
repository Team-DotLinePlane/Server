package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.TeamMember;
import cmc.hackaton.server.entity.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllRecords(String token);
}
