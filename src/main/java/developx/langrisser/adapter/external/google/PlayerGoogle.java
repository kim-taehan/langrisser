package developx.langrisser.adapter.external.google;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record PlayerGoogle(
        String nickname,
        String server,
        String point,
        String pointRank,
        String cristal,
        String cristalRank,
        List<String> oldNickname,
        Map<String, String> tiers
) {
}
