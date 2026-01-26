package developx.langrisser.application.match.dto;

import developx.langrisser.domain.match.GameMode;
import developx.langrisser.domain.match.Stage;
import developx.langrisser.domain.match.TurnOrder;

public record MatchSearchQuery (
        GameMode gameMode,
        Stage stage,
        TurnOrder turnOrder,
        Long opponentPlayerId
) {

    public MatchSearchQuery fromPlayerId(Long playerId) {
        return new MatchSearchQuery(this.gameMode, this.stage, this.turnOrder, playerId);
    }
}