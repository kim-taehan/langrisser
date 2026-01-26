package developx.langrisser.application.match.dto;

import lombok.Builder;

@Builder
public record PlayerMatchStats(
        int totalWin,
        int totalLose,
        int recentWin,
        int recentLose
) {
}
