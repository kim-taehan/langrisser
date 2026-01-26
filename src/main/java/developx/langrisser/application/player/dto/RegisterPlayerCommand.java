package developx.langrisser.application.player.dto;

import developx.langrisser.domain.player.ServerType;

public record RegisterPlayerCommand(
        String nickname,
       ServerType server
) {

    public static RegisterPlayerCommand init() {
        return new RegisterPlayerCommand("", ServerType.TISRAO_CASTLE);
    }
}
