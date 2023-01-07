package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.VoteMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteMemberRepository extends JpaRepository<VoteMember, Long> {
}
