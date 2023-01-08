package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.common.exception.team.TeamNotFoundException;
import cmc.hackaton.server.common.exception.vote.VoteNotFoundException;
import cmc.hackaton.server.dto.request.VoteAndTokenRequest;
import cmc.hackaton.server.dto.request.VoteRequest;
import cmc.hackaton.server.entity.*;
import cmc.hackaton.server.entity.constant.FoodCategory;
import cmc.hackaton.server.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class VoteService {

    private static final long VOTE_DURATION = 10L;
    private static final int NUM_OF_CATEGORY = 12;

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final VoteRepository voteRepository;
    private final VotePointRepository votePointRepository;
    private final VoteMemberRepository voteMemberRepository;

    @Transactional
    public void startNewVote(String memberToken, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);
        Member member = memberRepository.findByToken(memberToken)
                .orElseThrow(MemberNotFoundException::new);

        Vote vote = Vote.builder()
                .team(team)
                .voteLeader(member)
                .expiredTime(LocalDateTime.now().plusMinutes(VOTE_DURATION))
                .build();

        Arrays.stream(FoodCategory.values())
                .forEach(foodCategory -> {
                    VotePoint votePoint = VotePoint.builder()
                            .foodCategory(foodCategory)
                            .build();
                    votePointRepository.save(votePoint);
                    vote.addVotePoint(votePoint);
                });
        voteRepository.save(vote);

//        VoteMember voteMember = VoteMember.builder()
//                .member(member)
//                .vote(vote)
//                .isVoted(false)
//                .build();
//        voteMemberRepository.save(voteMember);
    }

    @Transactional
    public void vote(VoteRequest request) {
        // 만약 이미 투표한 사람이라면 exception throw
        // 투표했다는걸 마킹하고
        // 점수 더하기 해야 함
        Member member = memberRepository.findByToken(request.getMemberToken())
                .orElseThrow(MemberNotFoundException::new);
        Vote vote = voteRepository.findById(request.getVoteId())
                .orElseThrow(VoteNotFoundException::new);

        if (isVoted(member.getToken(), vote.getId())) {
            log.error("투표를 이미 한 유저입니다. memberToken={}, voteId={}", member.getToken(), vote.getId());
            throw new IllegalStateException("투표를 이미 한 유저입니다.");
        }

        // 투표 사실 저장
        VoteMember voteMember = VoteMember.builder()
                .member(member)
                .vote(vote)
                .isVoted(true)
                .build();
        voteMemberRepository.save(voteMember);

        // 점수 계산
        List<Integer> voteResults = request.getVoteResults();
        for (int i = 0; i < NUM_OF_CATEGORY; i++) {
            VotePoint votePoint = vote.getVotePointList().get(i);
            Integer point = votePoint.getPoint();
            votePoint.setPoint(point + voteResults.get(i));
        }
    }

    public boolean isVoted(String memberToken, Long voteId) {
        return voteMemberRepository.existsByMember_TokenAndVote_Id(memberToken, voteId);
    }

    // return 미정

    /**
     * 투표 수동 종료
     */
    @Transactional
    public String terminate(VoteAndTokenRequest request) {
        Member member = memberRepository.findByToken(request.getMemberToken())
                .orElseThrow(MemberNotFoundException::new);
        Vote vote = voteRepository.findById(request.getVoteId())
                .orElseThrow(VoteNotFoundException::new);

        if (!vote.getVoteLeader().equals(member)) {
            log.error("권한이 없는 사용자입니다. memberToken={}, voteId={}", member.getToken(), vote.getId());
            throw new IllegalStateException();
        }

        VotePoint votePoint = votePointRepository.findAll(Sort.by(Sort.Direction.DESC, "point")).get(0);
        FoodCategory selectedFootCategory = votePoint.getFoodCategory();
        vote.setIsCompleted(true);
        vote.setSelectedFoodCategory(selectedFootCategory);
        return votePoint.getFoodCategory().getRandomMenu(selectedFootCategory);
    }
}
