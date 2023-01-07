package cmc.hackaton.server.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

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

    @Builder
    private Team(String teamName, String teamCode) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.isAlarmActive = false;
    }
}
