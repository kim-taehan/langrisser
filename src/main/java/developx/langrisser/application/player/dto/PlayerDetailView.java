package developx.langrisser.application.player.dto;

import developx.langrisser.domain.player.*;
import lombok.Builder;

import java.util.List;

@Builder
public record PlayerDetailView (
        Long playerId,
        String nickname,
        ServerType server,
        MetricRank cristal,
        MetricRank summit,
        List<String> previousNicknames,
        String comment,
        SeasonTier tier
) {
    public static PlayerDetailView from(Player player) {
        return PlayerDetailView.builder()
                .nickname(player.getNickname())
                .playerId(player.getId())
                .server(player.getServer())
                .previousNicknames(convertFrom(player))
                .cristal(player.getCrystal())
                .summit(player.getSummit())
                .comment(player.getComment())
                .tier(player.getTier())
                .build();
    }

    private static List<String> convertFrom(Player player) {
        return player.getNicknameHistories()
                .stream()
                .map(PlayerNicknameHistory::getNickname)
                .toList();
    }

    public String badgeClass() {
        if (tier.order() <= 4) return "bg-danger";        // TOP 4 ~ 1
        if (tier.order() <= 32) return "bg-warning";      // 본선
        if (tier.order() <= 256) return "bg-info";        // 예선
        if (tier.order() <= 1000) return "bg-success";
        return "bg-secondary";
    }
}
