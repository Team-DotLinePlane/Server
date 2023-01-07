package cmc.hackaton.server.dto.request;

import cmc.hackaton.server.dto.MemberDto;
import cmc.hackaton.server.dto.TeamDto;
import cmc.hackaton.server.entity.Team;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor  // @RequestBody에서 쓰려면 default constructor가 있어야 한다.
@Getter
public class TeamRequest {

    private String teamName;

    public static TeamRequest of(String teamName) {
        return new TeamRequest(teamName);
    }

    public TeamDto toDto() {
        return TeamDto.of(
                teamName
        );
    }
}
