package cmc.hackaton.server.entity.vote;

import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "vote_history_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private VoteHistory voteHistory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory category;

    @Column(nullable = false)
    private int value;

    @Builder
    private Score(VoteHistory voteHistory, FoodCategory category) {
        this.voteHistory = voteHistory;
        this.category = category;
        this.value = 0;
    }
}
