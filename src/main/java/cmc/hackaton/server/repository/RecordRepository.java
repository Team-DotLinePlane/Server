package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
