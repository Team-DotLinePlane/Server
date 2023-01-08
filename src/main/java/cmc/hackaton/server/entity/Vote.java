package cmc.hackaton.server.entity;

import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    @Setter
    @Enumerated(EnumType.STRING)
    private FoodCategory selectedFoodCategory;

    @Column(nullable = false)
    private LocalDateTime expiredTime;

    @Setter
    private Boolean isCompleted;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private final List<VotePoint> votePointList = new ArrayList<>();

    @Builder
    private Vote(Team team, Member voteLeader, LocalDateTime expiredTime) {
        this.team = team;
        this.voteLeader = voteLeader;
        this.expiredTime = expiredTime;
        this.isCompleted = false;
    }

    public void addVotePoint(VotePoint votePoint) {
        getVotePointList().add(votePoint);
        votePoint.setVote(this);
    }
}
