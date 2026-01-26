package developx.langrisser.application.player.dto;

import developx.langrisser.domain.player.MetricRank;
import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.SeasonTier;
import developx.langrisser.domain.player.ServerType;

import java.time.LocalDate;

public record PlayerSummaryView (
        Long playerId,
        String nickname,
        ServerType server,
        MetricRank cristal,
        MetricRank summit,
        LocalDate lastMatchDate,
        SeasonTier tier
) {
    public static PlayerSummaryView from(Player player) {
        return new PlayerSummaryView(
                player.getId(),
                player.getNickname(),
                player.getServer(),
                player.getCrystal(),
                player.getSummit(),
                player.getLastMatchDate(),
                player.getTier()
        );
    }
}
