package cmc.hackaton.server.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RecordListResponse {

    private List<RecordResponse> recordList;

    public static RecordListResponse of(List<RecordResponse> recordResponses) {
        return new RecordListResponse(recordResponses);
    }
}
