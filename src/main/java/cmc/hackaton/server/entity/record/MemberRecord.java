package cmc.hackaton.server.entity.record;

import cmc.hackaton.server.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRecord extends Record {

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public MemberRecord(Member member) {
        this.member = member;
    }
}
