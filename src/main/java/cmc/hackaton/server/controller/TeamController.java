package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.request.MemberSaveRequest;
import cmc.hackaton.server.dto.request.MemberUpdateRequest;
import cmc.hackaton.server.dto.request.TeamJoinRequest;
import cmc.hackaton.server.dto.request.TeamRequest;
import cmc.hackaton.server.dto.response.MemberResponse;
import cmc.hackaton.server.dto.response.TeamJoinResponse;
import cmc.hackaton.server.dto.response.TeamResponse;
import cmc.hackaton.server.dto.response.TeamWithMembersResponse;
import cmc.hackaton.server.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Team")
@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamController {

    private final TeamService teamService;

    @Operation(
            summary = "그룹 조회",
            description = "`token`에 해당하는 그룹 정보를 조회한다."
    )
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamWithMembersResponse> findMember(@PathVariable Long teamId) {
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
            description = "`token`에 해당하는 그룹에 참여한다"
    )
    @PutMapping
    public ResponseEntity<TeamJoinResponse> joinGroup(@RequestBody TeamJoinRequest request) {
        return ResponseEntity.ok(TeamJoinResponse.from(teamService.joinTeam(request)));
    }
}
