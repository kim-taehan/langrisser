package developx.langrisser.application.dashboard.providerd;

import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.WinLoseStatsView;

public interface DashboardFinder {

    WinLoseStatsView getTurnOrderStats(DashboardPeriod period);

    WinLoseStatsView getGameModeStats(DashboardPeriod period);

    WinLoseStatsView getStageStats(DashboardPeriod period);
}
