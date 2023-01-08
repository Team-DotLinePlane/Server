package cmc.hackaton.server.dto.response;

import cmc.hackaton.server.dto.RecordDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecordResponse {

    private String dtype;

    private String selectedMenu;

//    public static RecordResponse of(Member member, Team team, String dtype, String selectedMenu, List<RecordResponse> recordList ) {
//        return new RecordResponse(member, team, dtype, selectedMenu, recordList);
//    }

    public static RecordResponse from(RecordDto dto) {
        return new RecordResponse(
                dto.getDtype(),
                dto.getSelectedMenu()
        );
    }
}
