package developx.langrisser.application.player.dto;

import developx.langrisser.application.match.dto.PlayerMatchStats;
import lombok.Builder;

@Builder
public record PlayerStatsSummaryView(
        int totalWin,
        int totalLose,
        int totalRate,
        int recentWin,
        int recentLose,
        int recentRate
) {
    public static PlayerStatsSummaryView from(PlayerMatchStats stats) {
        int totalWin = stats.totalWin();
        int totalLose = stats.totalLose();
        int totalRate = calculateRate(totalWin, totalLose);

        int recentWin = stats.recentWin();
        int recentLose = stats.recentLose();
        int recentRate = calculateRate(recentWin, recentLose);

        return new PlayerStatsSummaryView(
                totalWin,
                totalLose,
                totalRate,
                recentWin,
                recentLose,
                recentRate
        );
    }

    private static int calculateRate(int win, int lose) {
        int total = win + lose;
        if (total == 0) {
            return 0;
        }
        return (int) Math.round((win * 100.0) / total);
    }
}