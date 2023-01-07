package cmc.hackaton.server.entity.vote;

import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class VoteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "vote_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

    @JoinColumn(name = "member_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    // TODO: 어떤 Collection type이 적절한지 고려 필요
    // TODO: Entity 생성 시 초기화 필요
    @OneToMany(mappedBy = "voteHistory", cascade = CascadeType.ALL)
    private final Set<Rank> ranks = new HashSet<>();

    @Builder
    private VoteHistory(Vote vote, Member member) {
        this.vote = vote;
        this.member = member;

        // Entity 생성 시 <각 category : 0>의 값을 갖는 Set 생성.
        Arrays.stream(FoodCategory.values())
                .forEach(foodCategory -> ranks.add(
                        Rank.builder()
                                .voteHistory(this)
                                .category(foodCategory)
                                .build()
                ));
    }
}
