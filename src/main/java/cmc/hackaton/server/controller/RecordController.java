package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.request.TeamRequest;
import cmc.hackaton.server.dto.response.*;
import cmc.hackaton.server.service.RecordService;
import cmc.hackaton.server.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Record")
@RequiredArgsConstructor
@RequestMapping("/api/records")
@RestController
public class RecordController {
    private final RecordService recordService;
    @GetMapping
    public ResponseEntity<RecordListResponse> findRecord(@RequestParam String token) {
        return ResponseEntity.ok(RecordListResponse.of(
                recordService.findAllRecords(token).stream()
                        .map(RecordResponse::from)
                        .collect(Collectors.toList()))
        );
    }

    @Operation(
            summary = "레코드 생성",
            description = "유저 정보(`token`)를 전달받아 레코드를 생성한다"
    )
    @PostMapping
    public ResponseEntity<Void> saveRecord(@RequestParam String token) {
        recordService.addRecord(token, request.getTeamCode());
        return ResponseEntity.noContent().build();
    }
}
