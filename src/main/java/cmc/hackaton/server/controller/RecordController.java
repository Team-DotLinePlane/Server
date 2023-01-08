package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.response.RecordListResponse;
import cmc.hackaton.server.dto.response.RecordResponse;
import cmc.hackaton.server.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Tag(name = "Record")
@RequiredArgsConstructor
@RequestMapping("/api/records")
@RestController
public class RecordController {
    private final RecordService recordService;

    @Operation(
            summary = "유저의 개인의 모든 기록 획득",
            description = "유저의 개인의 모든 기록 획득"
    )
    @GetMapping
    public ResponseEntity<RecordListResponse> findRecord(@RequestParam long teamId) {
        return ResponseEntity.ok(
            RecordListResponse.of(recordService.findAllRecords(teamId).stream()
                .map(RecordResponse::from)
                .collect(Collectors.toList()))
        );
    }

    //@Operation(
        //    summary = "레코드 생성",
      //      description = "유저 정보(`token`)를 전달받아 레코드를 생성한다"
    //)
    //@PostMapping
    //public ResponseEntity<Void> saveRecord(@RequestParam String token) {
     ////   recordService.addRecord(token, request.getTeamCode());
     //   return ResponseEntity.noContent().build();
    //}
}
