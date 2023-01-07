package cmc.hackaton.server.entity.vote;

import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @JoinColumn(name = "vote_leader_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Member voteLeader;

    // 투표 결과로 선택된 category
    // 투표 종료 후 값을 설정할 것이므로 nullable
    @Enumerated(EnumType.STRING)
    private FoodCategory selectedFoodCategory;

    @Column(nullable = false)
    private LocalDateTime expiredTime;  // TODO: LocalTime으로 전환 고려

    @OneToMany(mappedBy = "vote")
    private final List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private final List<VoteHistory> voteHistories = new ArrayList<>();

    @Builder
    private Vote(Team team, LocalDateTime expiredTime) {
        this.team = team;
        this.expiredTime = expiredTime;
    }
}
