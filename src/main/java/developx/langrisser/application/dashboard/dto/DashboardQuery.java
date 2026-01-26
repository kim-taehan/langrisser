package developx.langrisser.application.dashboard.dto;

import developx.langrisser.application.dashboard.DashboardPeriod;

public record DashboardQuery(DashboardPeriod period) {

    public DashboardQuery {
        if (period == null) {
            period = DashboardPeriod.ALL;
        }
    }
}
