package cmc.hackaton.server.entity;


import cmc.hackaton.server.entity.vote.Vote;
import cmc.hackaton.server.entity.vote.VoteHistory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Column(nullable = false, unique = true)
    private String identifier;  // 익명 유저의 UUID, OAuth 도입 시 email 병행 저장

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "member")
    private final List<GroupMembers> groupMembers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<MemberHistory> memberHistories = new ArrayList<>();

    @Builder
    private Member(String identifier, String nickname, Vote vote) {
        this.identifier = identifier;
        this.nickname = nickname;
        this.vote = vote;
    }
}