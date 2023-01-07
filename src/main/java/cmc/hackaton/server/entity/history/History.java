package cmc.hackaton.server.entity.history;

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
public abstract class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String selectedMenu;

    public static History of(Object type, String selectedMenu) {
        if (type instanceof Member) {
            return new MemberHistory((Member) type);
        } else if (type instanceof Team) {
            return new TeamHistory((Team) type);
        } else {
            throw new BadRequestException("History를 생성하는데 필요한 정보가 잘못되었습니다.");
        }
    }

    private History(String selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
}
