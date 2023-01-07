package cmc.hackaton.server.dto.response;

import cmc.hackaton.server.dto.RecordDto;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.dto.TeamWithMembersDto;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecordResponse {

    private Member member;

    private Team team;

    private String dtype;

    private String selectedMenu;
    private List<RecordResponse> recordList;

    public static RecordResponse of(Member member, Team team, String dtype, String selectedMenu, List<RecordResponse> recordList ) {
        return new RecordResponse(member, team, dtype, selectedMenu, recordList);
    }

    public static RecordResponse from(RecordDto dto) {
        return new TeamResponse(
                dto.getMember(),
                dto.getTeam(),
                dto.getDType(),
                dto.getSelectedMenu(),
                dto.getRecrodList()
        );
    }
}
