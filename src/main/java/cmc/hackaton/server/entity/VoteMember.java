package cmc.hackaton.server.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VoteMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    @JoinColumn(name = "vote_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Vote vote;

    @Column(nullable = false)
    private boolean isVoted;

    @Builder
    private VoteMember(Member member, Vote vote, boolean isVoted) {
        this.member = member;
        this.vote = vote;
        this.isVoted = isVoted;
    }
}
