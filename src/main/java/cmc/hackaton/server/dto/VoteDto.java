package cmc.hackaton.server.dto;

import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.constant.FoodCategory;
import cmc.hackaton.server.entity.Vote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class VoteDto {

    private Team team;
    private Member voteLeader;
    private FoodCategory selectedFoodCategory;
    private LocalDateTime expiredTime;
    private Boolean isCompleted;

    public static VoteDto of(Team team, Member voteLeader, FoodCategory selectedFoodCategory, LocalDateTime expiredTime, Boolean isCompleted) {
        return new VoteDto(team, voteLeader, selectedFoodCategory, expiredTime, isCompleted);
    }

    public static VoteDto from(Vote vote) {
        return new VoteDto(
                vote.getTeam(),
                vote.getVoteLeader(),
                vote.getSelectedFoodCategory(),
                vote.getExpiredTime(),
                vote.getIsCompleted()
        );
    }

    public Vote toEntity() {
        return Vote.builder()
                .team(team)
                .voteLeader(voteLeader)
                .expiredTime(expiredTime)
                .build();
    }
}
