package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.request.VoteAndTokenRequest;
import cmc.hackaton.server.dto.request.VoteRequest;
import cmc.hackaton.server.dto.request.VoteStartRequest;
import cmc.hackaton.server.dto.response.IsVotedResponse;
import cmc.hackaton.server.dto.response.MenuResponse;
import cmc.hackaton.server.entity.constant.FoodCategory;
import cmc.hackaton.server.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Tag(name = "Vote")
@RequiredArgsConstructor
@RequestMapping("/api/votes")
@RestController
public class VoteController {

    private final VoteService voteService;

    @Operation(
            summary = "투표 시작",
            description = "`memberToken`에 해당하는 사용자가 `teamId`에 해당하는 그룹에서 투표를 시작합니다."
    )
    @PostMapping("/start")
    public ResponseEntity<Void> startNewVote(@RequestBody VoteStartRequest request) {
        voteService.startNewVote(request.getMemberToken(), request.getTeamId());

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "투표",
            description = "`memberToken`에 해당하는 사용자가 `voteId` 투표에 투표 결과를 제출합니다.\n\n" +
                    "투표 결과에 관한 상세 정보는 notion 문서 참고 (팀내 규약)"
    )
    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteRequest request) {
        voteService.vote(request);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "투표 여부 확인",
            description = "`memberToken`에 해당하는 사용자가 `voteId` 투표에 투표한 적 있는지 확인한다.\n" +
                    "투표한 적 있다면 `true`, `투표한 적이 없다면 `false`."
    )
    @GetMapping("/check/{voteId}")
    public IsVotedResponse isVoted(
            @RequestParam String memberToken,
            @PathVariable Long voteId
    ) {
        return IsVotedResponse.of(voteService.isVoted(memberToken, voteId));
    }

    @Operation(
            summary = "투표 수동 종료",
            description = "`memberToken`에 해당하는 사용자가 `voteId` 투표를 종료한다.\n" +
                    "응답으로 선택된 메뉴가 반환된다."
    )
    @PostMapping("/termination")
    public ResponseEntity<MenuResponse> terminate(@RequestBody VoteAndTokenRequest request) {
        return ResponseEntity.ok(MenuResponse.of(voteService.terminate(request)));
    }
}
