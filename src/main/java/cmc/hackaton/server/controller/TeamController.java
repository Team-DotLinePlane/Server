package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.request.TeamJoinRequest;
import cmc.hackaton.server.dto.request.TeamRequest;
import cmc.hackaton.server.dto.response.TeamListResponse;
import cmc.hackaton.server.dto.response.TeamResponse;
import cmc.hackaton.server.dto.response.TeamWithMembersResponse;
import cmc.hackaton.server.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Team")
@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamController {

    private final TeamService teamService;

    @Operation(
            summary = "그룹 목록 조회",
            description = "`memberToken`에 해당하는 유저가 포함된 그룹을 전부 조회한다."
    )
    @GetMapping
    public ResponseEntity<TeamListResponse> findTeamList(@RequestParam String memberToken) {
        return ResponseEntity.ok(TeamListResponse.of(
                teamService.findAllTeams(memberToken).stream()
                        .map(TeamResponse::from)
                        .collect(Collectors.toList()))
        );
    }

    @Operation(
            summary = "그룹 조회",
            description = "`teamId`에 해당하는 그룹 정보를 조회한다."
    )
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamWithMembersResponse> findTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(TeamWithMembersResponse.from(teamService.findTeam(teamId)));
    }

    @Operation(
            summary = "그룹 생성",
            description = "그룹을 생성하는 유저 정보(`token`)과 그룹 이름을 전달받아\n" +
                    "그룹을 생성하고 그룹 코드를 응답한다."
    )
    @PostMapping
    public ResponseEntity<TeamResponse> saveTeam(@RequestBody TeamRequest request) {
        return ResponseEntity.ok(
                TeamResponse.from(teamService.saveTeam(request.getMemberToken(), request.toDto()))
        );
    }

    @Operation(
            summary = "그룹 참여",
            description = "`token`에 해당하는 유저가 `teamCode`에 해당하는 그룹에 참여한다."
    )
    @PostMapping
    public ResponseEntity<Void> joinTeam(@RequestBody TeamJoinRequest request) {
        teamService.joinTeam(request.getToken(), request.getTeamCode());
        return ResponseEntity.noContent().build();
    }
}
