package cmc.hackaton.server.dto;

import cmc.hackaton.server.dto.response.RecordResponse;
import cmc.hackaton.server.entity.Member;
import cmc.hackaton.server.entity.Team;
import cmc.hackaton.server.entity.record.MemberRecord;
import cmc.hackaton.server.entity.record.Record;
import cmc.hackaton.server.entity.record.TeamRecord;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecordDto {

    private String dtype;

    private String selectedMenu;

    private static RecordDto of(Record record, String selectedMenu) {
        if (record instanceof MemberRecord) {
            return new RecordDto("member", selectedMenu);
        } else {
            return new RecordDto("team", selectedMenu);
        }
    }

    public static RecordDto from(Record record) {
        return RecordDto.of(
            record,
            record.getSelectedMenu()
            );
    }

}
