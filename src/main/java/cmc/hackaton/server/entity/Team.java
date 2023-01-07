package cmc.hackaton.server.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import cmc.hackaton.server.entity.vote.Vote;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_leader_id", nullable = false)
    private Member teamLeader;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false, unique = true)
    private String teamCode;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private final List<TeamHistory> teamHistory = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private final List<TeamMembers> teamMembers = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private final List<Vote> vote = new ArrayList<>();

    @Builder
    private Team(Member teamLeader, String teamName, String teamCode) {
        this.teamLeader = teamLeader;
        this.teamName = teamName;
        this.teamCode = teamCode;
    }
}
