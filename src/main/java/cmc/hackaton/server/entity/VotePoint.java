package cmc.hackaton.server.entity;

import cmc.hackaton.server.entity.constant.FoodCategory;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class VotePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

    @Setter
    private Integer point;

    @Builder
    private VotePoint(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
        this.point = 0;
    }
}
