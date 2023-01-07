package cmc.hackaton.server.entity;


import lombok.*;

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
    private String token;  // 익명 유저의 UUID

    @Setter
    @Column(nullable = false)
    private String nickname;

    @Builder
    private Member(String token, String nickname) {
        this.token = token;
        this.nickname = nickname;
    }
}