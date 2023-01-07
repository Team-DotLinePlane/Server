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
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "vote_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

    @Column(nullable = false)
    private boolean isVoted;

    @Builder

    private VoteMember(Member member, Vote vote) {
        this.member = member;
        this.vote = vote;
        this.isVoted = false;
    }
}
