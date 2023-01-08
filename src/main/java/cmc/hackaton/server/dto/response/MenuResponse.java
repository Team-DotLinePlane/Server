package cmc.hackaton.server.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MenuResponse {

    private String menu;

    public static MenuResponse of(String menu) {
        return new MenuResponse(menu);
    }
}
