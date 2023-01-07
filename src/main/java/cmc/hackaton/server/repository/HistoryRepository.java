package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
