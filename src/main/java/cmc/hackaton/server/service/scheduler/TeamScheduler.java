package cmc.hackaton.server.service.scheduler;

import cmc.hackaton.server.common.FCMHandler;
import cmc.hackaton.server.repository.TeamMemberRepository;
import cmc.hackaton.server.repository.TeamRepository;
import cmc.hackaton.server.service.TeamService;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TeamScheduler {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public TeamScheduler(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Scheduled(fixedRate = 10000)
    @Transactional(readOnly = true)
    public void MealTimeScheduler() {
        this.teamRepository.findTeamsByMealTime(LocalDateTime.now())
            .forEach(team -> teamMemberRepository.findAllByTeam_Id(team.getId())
            .forEach(teamMember -> FCMHandler.notiMealTime(teamMember.getMember().getToken())));
    }
}
