package developx.langrisser.application.match.dto;

import developx.langrisser.domain.match.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record RegisterMatchCommand(
        Long opponentPlayerId,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate matchDate,

        GameMode gameMode,

        Stage stage,

        Outcome outcome,

        TurnOrder turnOrder,

        String memo
) {
    public static RegisterMatchCommand init(Long playerId) {
        return new RegisterMatchCommand(
                playerId,
                LocalDate.now(),
                GameMode.CASUAL,
                null,
                Outcome.WIN,
                TurnOrder.FIRST,
                ""
        );
    }

    public Match toEntity() {
        return Match.builder()
                .opponentPlayerId(this.opponentPlayerId)
                .matchDate(this.matchDate)
                .mode(this.gameMode)
                .stage(this.stage)
                .outcome(this.outcome)
                .turnOrder(this.turnOrder)
                .memo(this.memo)
                .build();
    }
}
