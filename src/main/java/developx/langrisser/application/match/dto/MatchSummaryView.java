package developx.langrisser.application.match.dto;

import developx.langrisser.application.player.dto.PlayerSummaryView;
import developx.langrisser.application.utils.DateRelativeFormatter;
import developx.langrisser.domain.match.*;
import developx.langrisser.domain.player.Player;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MatchSummaryView(
        Long matchId,
        PlayerSummaryView opponentPlayer,
        LocalDate matchDate,
        String relativeDate,
        GameMode gameMode,
        Stage stage,
        Outcome outcome,
        TurnOrder turnOrder,
        String memo
) {

    public static MatchSummaryView from(Match match, Player opponentPlayer) {
        return MatchSummaryView.builder()
                .matchId(match.getId())
                .opponentPlayer(PlayerSummaryView.from(opponentPlayer))
                .matchDate(match.getMatchDate())
                .relativeDate(DateRelativeFormatter.format(match.getMatchDate()))
                .gameMode(match.getMode())
                .stage(match.getStage())
                .outcome(match.getOutcome())
                .turnOrder(match.getTurnOrder())
                .memo(match.getMemo())
                .build();

    }
}
