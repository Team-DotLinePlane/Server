package cmc.hackaton.server.repository;

import cmc.hackaton.server.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findAllByMember_Token(String token);

    List<TeamMember> findAllByTeam_Id(Long team_id);
}
