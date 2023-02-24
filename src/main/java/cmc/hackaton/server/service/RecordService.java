package cmc.hackaton.server.service;

import cmc.hackaton.server.common.exception.member.MemberNotFoundException;
import cmc.hackaton.server.dto.RecordDto;
import cmc.hackaton.server.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

import cmc.hackaton.server.repository.TeamRecordRepository;
import cmc.hackaton.server.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecordService {


    private final TeamRepository teamRepository;
    //private final TeamRepository teamRepository;
    private final TeamRecordRepository teamRecordRepository;

    public List<RecordDto> findAllRecords(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(MemberNotFoundException::new);
        return teamRecordRepository.findAllByTeam_Id(teamId).stream()
            .map(RecordDto::from)
            .collect(Collectors.toList());

    }

}
