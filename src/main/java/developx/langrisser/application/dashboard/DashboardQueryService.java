package developx.langrisser.application.dashboard;

import developx.langrisser.application.dashboard.dto.WinLoseStatsView;
import developx.langrisser.application.dashboard.providerd.DashboardFinder;
import developx.langrisser.application.match.required.MatchStore;
import developx.langrisser.domain.match.GameMode;
import developx.langrisser.domain.match.Stage;
import developx.langrisser.domain.match.TurnOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardQueryService implements DashboardFinder {

    private final MatchStore store;

    @Override
    public WinLoseStatsView getTurnOrderStats(DashboardPeriod period) {
        return store.aggregate(TurnOrder.class, period);

    }

    @Override
    public WinLoseStatsView getGameModeStats(DashboardPeriod period) {
        return store.aggregate(GameMode.class, period);
    }

    @Override
    public WinLoseStatsView getStageStats(DashboardPeriod period) {
        return store.aggregate(Stage.class, period);
    }
}
