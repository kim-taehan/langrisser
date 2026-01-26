package developx.langrisser.adapter.web;

import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.DashboardQuery;
import developx.langrisser.application.dashboard.providerd.DashboardFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class DashboardViewController {

    private final DashboardFinder finder;

    @GetMapping({"/dashboard", "/"})
    public String dashboard(
            Model model,
            @ModelAttribute("query") DashboardQuery query
    ) {
        DashboardPeriod period = query.period();
        model.addAttribute("period", period);
        model.addAttribute("turnOrderStats", finder.getTurnOrderStats(period));
        model.addAttribute("gameModeStats", finder.getGameModeStats(period));
        model.addAttribute("stageStats", finder.getStageStats(period));
        return "dashboard";
    }
}
