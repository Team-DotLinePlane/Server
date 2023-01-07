package cmc.hackaton.server.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;  // 익명 유저의 UUID

    @Column(nullable = false)
    private String nickname;

    @Builder
    private Member(String identifier, String nickname) {
        this.identifier = identifier;
        this.nickname = nickname;
    }
}