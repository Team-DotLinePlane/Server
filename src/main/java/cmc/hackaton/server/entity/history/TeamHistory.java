package cmc.hackaton.server.entity.history;

import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Team")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamHistory extends History {

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public TeamHistory(Team team) {
        this.team = team;
    }
}
