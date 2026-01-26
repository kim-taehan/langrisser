package developx.langrisser.application.player.dto;

import developx.langrisser.domain.player.ServerType;

public record FindPlayerQuery(
        String nickname,
        ServerType server,
        Boolean specialOnly
) {
}
