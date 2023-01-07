package cmc.hackaton.server.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JoinColumn(name = "group_members_id", nullable = false)
    private List<GroupMembers> groupMembers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_leader_id", nullable = false)
    private Member groupLeader;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false, unique = true)
    private String groupCode;

    @Builder
    private Group(Member groupLeader, String groupName, String groupCode) {
        this.groupLeader = groupLeader;
        this.groupName = groupName;
        this.groupCode = groupCode;
    }
}
