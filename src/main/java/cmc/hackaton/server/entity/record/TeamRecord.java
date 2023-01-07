package cmc.hackaton.server.entity.record;

import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Team")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRecord extends Record {

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public TeamRecord(Team team) {
        this.team = team;
    }
}
