package cmc.hackaton.server.entity.vote;

import cmc.hackaton.server.entity.Group;
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

    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

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
    private Vote(Group group, LocalDateTime expiredTime) {
        this.group = group;
        this.expiredTime = expiredTime;
    }
}
