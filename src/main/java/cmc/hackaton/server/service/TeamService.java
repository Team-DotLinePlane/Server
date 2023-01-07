package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.team.TeamNotFoundException;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.dto.TeamWithMembersDto;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.repository.MemberRepository;
import cmc.hackaton.server.repository.TeamMemberRepository;
import cmc.hackaton.server.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamWithMembersDto findTeam(Long teamId) {
        return TeamWithMembersDto.from(
                teamRepository.findById(teamId)
                        .orElseThrow(TeamNotFoundException::new)
        );
    }

    public List<TeamDto> findAllTeams(String token) {
        return teamMemberRepository.findAllByMember_Token(token).stream()
                .map(teamMember -> TeamDto.from(teamMember.getTeam()))
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamDto saveTeam(String memberToken, TeamDto dto) {
        String teamCode;
        do {
            teamCode = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        } while (teamRepository.existsByTeamCode(teamCode));
        Team team = dto.toEntity(teamCode);
        team.addTeamMember(memberRepository.getByToken(memberToken));
        teamRepository.save(team);
        return TeamDto.from(team);
    }
}
