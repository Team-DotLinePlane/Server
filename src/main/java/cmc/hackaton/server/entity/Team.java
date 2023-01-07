package cmc.hackaton.server.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false, unique = true)
    private String teamCode;

    private LocalTime mealTime;

    @Column(nullable = false)
    private Boolean isAlarmActive;

    @OneToMany(mappedBy = "team")
    private final List<TeamMember> teamMembers = new ArrayList<>();

    @Builder
    private Team(String teamName, String teamCode) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.mealTime = LocalTime.of(12, 0);
        this.isAlarmActive = false;
    }

    public void addTeamMember(TeamMember teamMember) {
        getTeamMembers().add(teamMember);
    }
}
