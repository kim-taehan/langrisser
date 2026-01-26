package developx.langrisser.application.player.dto;

import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.ServerType;

public record RegisterPlayerResult(
        Long playerId,
        String nickname,
        ServerType server
) {
    public static RegisterPlayerResult from(Player player) {
        return new RegisterPlayerResult(player.getId(), player.getNickname(), player.getServer());
    }
}
