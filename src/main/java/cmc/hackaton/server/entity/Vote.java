package cmc.hackaton.server.entity;

import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Boolean isCompleted;

    @Builder

    private Vote(Team team, Member voteLeader, FoodCategory selectedFoodCategory, LocalDateTime expiredTime, Boolean isCompleted) {
        this.team = team;
        this.voteLeader = voteLeader;
        this.selectedFoodCategory = selectedFoodCategory;
        this.expiredTime = expiredTime;
        this.isCompleted = isCompleted;
    }
}
