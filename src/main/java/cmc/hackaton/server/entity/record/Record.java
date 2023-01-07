package cmc.hackaton.server.entity.record;

import cmc.hackaton.server.common.exception.BadRequestException;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String selectedMenu;

    public static Record of(Object type, String selectedMenu) {
        if (type instanceof Member) {
            return new MemberRecord((Member) type);
        } else if (type instanceof Team) {
            return new TeamRecord((Team) type);
        } else {
            throw new BadRequestException("기록을 생성하는데 필요한 정보가 잘못되었습니다.");
        }
    }

    private Record(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
}
