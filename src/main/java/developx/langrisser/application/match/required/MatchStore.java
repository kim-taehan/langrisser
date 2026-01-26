package developx.langrisser.application.match.required;

import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.WinLoseStatsView;
import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.PlayerMatchStats;
import developx.langrisser.domain.match.Match;
import developx.langrisser.domain.match.TurnOrder;

import java.util.List;
import java.util.Optional;

public interface MatchStore {

    Match save(Match match);

    Optional<Match> findById(Long matchId);

    List<Match> findAll(MatchSearchQuery request);

    void remove(Match match);

    PlayerMatchStats loadHistory(MatchSearchQuery updatedQuery);

    WinLoseStatsView aggregate(Class<? extends Enum<?>> statType, DashboardPeriod period);
}
