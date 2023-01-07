package cmc.hackaton.server.dto;

import cmc.hackaton.server.dto.response.RecordResponse;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.record.MemberRecord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecordDto {

    private Member member;

    private Team team;

    private String dtype;

    private String selectedMenu;
    private List<RecordResponse> recordList;

    public static RecordDto of(String selectedMenu) {
        return new RecordDto(null, null, null, selectedMenu, null);
    }

    public static RecordDto from(MemberRecord memberRecord) {
        return new RecordDto(
        );
    }

}
