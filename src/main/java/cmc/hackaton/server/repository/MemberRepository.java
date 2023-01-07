package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByToken(String token);

    Member getByToken(String token);

    boolean existsByToken(String token);
}
