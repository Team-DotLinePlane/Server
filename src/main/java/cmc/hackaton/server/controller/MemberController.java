package cmc.hackaton.server.controller;

import cmc.hackaton.server.dto.request.MemberUpdateRequest;
import cmc.hackaton.server.dto.request.MemberSaveRequest;
import cmc.hackaton.server.dto.response.MemberResponse;
import cmc.hackaton.server.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Member")
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "유저 조회",
            description = "`token`에 해당하는 유저 정보를 조회한다."
    )
    @GetMapping
    public ResponseEntity<MemberResponse> findMember(@RequestParam String token) {
        return ResponseEntity.ok(MemberResponse.from(memberService.findMember(token)));
    }

    @Operation(
            summary = "유저 저장(게스트 로그인)",
            description = "App 설치 후 처음 진입 시 (FCM)`token`을 전달하면 해당 기기의 유저를 서버에 등록한다."
    )
    @PostMapping
    public ResponseEntity<Void> saveMember(@RequestBody MemberSaveRequest request) {
        memberService.saveMember(request.toDto());
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "유저 닉네임 수정",
            description = "`token`에 해당하는 유저의 닉네임을 수정한다."
    )
    @PutMapping
    public ResponseEntity<Void> updateMemberNickname(@RequestBody MemberUpdateRequest request) {
        memberService.updateMemberNickname(request.toDto());
        return ResponseEntity.noContent().build();
    }
}
