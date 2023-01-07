package cmc.hackaton.server.entity.history;

import cmc.hackaton.server.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberHistory extends History {

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public MemberHistory(Member member) {
        this.member = member;
    }
}
