package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.common.exception.team.TeamNotFoundException;
import cmc.hackaton.server.common.exception.vote.VoteNotFoundException;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.dto.TeamWithMembersDto;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.TeamMember;
import cmc.hackaton.server.entity.Vote;
import cmc.hackaton.server.repository.MemberRepository;
import cmc.hackaton.server.repository.TeamMemberRepository;
import cmc.hackaton.server.repository.TeamRepository;
import cmc.hackaton.server.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final VoteRepository voteRepository;

    public TeamWithMembersDto findTeam(Long teamId) {
        boolean isVoteProgress = false;
        Long voteId = -1L;
        Optional<Vote> voteOptional = voteRepository.findByTeam_IdOrderByIdDesc(teamId);
        if (voteOptional.isPresent()) {
            Vote vote = voteOptional.get();
            isVoteProgress = !vote.getIsCompleted();
            voteId = vote.getId();
        }

        return TeamWithMembersDto.from(
                teamRepository.findById(teamId)
                        .orElseThrow(TeamNotFoundException::new),
                isVoteProgress,
                voteId
        );
    }

    public List<TeamDto> findAllTeams(String token) {
        return teamMemberRepository.findAllByMember_Token(token).stream()
                .map(teamMember -> {
                    boolean isVoteProgress = false;
                    Optional<Vote> voteOptional = voteRepository.findByTeam_IdOrderByIdDesc(teamMember.getTeam().getId());
                    if (voteOptional.isPresent()) {
                        Vote vote = voteOptional.get();
                        isVoteProgress = !vote.getIsCompleted();
                    }
                    return TeamDto.from(teamMember.getTeam(), isVoteProgress);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamDto saveTeam(String memberToken, TeamDto dto) {
        validateMemberToken(memberToken);

        String teamCode;
        do {
            teamCode = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        } while (teamRepository.existsByTeamCode(teamCode));

        Team team = dto.toEntity(teamCode);
        Member member = memberRepository.findByToken(memberToken)
                .orElseThrow(MemberNotFoundException::new);
        TeamMember teamMember = TeamMember.builder()
                .member(member)
                .team(team)
                .build();
        teamMemberRepository.save(teamMember);
        team.addTeamMember(teamMember);
        teamRepository.save(team);
        return TeamDto.from(team, false);
    }

    @Transactional
    public void joinTeam(String memberToken, String teamCode) {
        Team team = teamRepository.findByTeamCode(teamCode)
                .orElseThrow(TeamNotFoundException::new);
        Member member = memberRepository.findByToken(memberToken)
                .orElseThrow(MemberNotFoundException::new);
        TeamMember teamMember = TeamMember.builder()
                .member(member)
                .team(team)
                .build();
        teamMemberRepository.save(teamMember);
        team.addTeamMember(teamMember);
    }

    private void validateMemberToken(String memberToken) {
        if (!memberRepository.existsByToken(memberToken)) {
            throw new MemberNotFoundException();
        }
    }
}
