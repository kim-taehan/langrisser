package developx.langrisser.application.dashboard.dto;

import java.util.List;

public record WinLoseStatsView(
        List<LabeledEnumStat> stats
) {
}
