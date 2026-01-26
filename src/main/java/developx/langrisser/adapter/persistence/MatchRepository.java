package developx.langrisser.adapter.persistence;

import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.WinLoseStatsView;
import developx.langrisser.domain.match.Match;
import developx.langrisser.domain.match.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>, MatchRepositoryCustom {
    int countByOpponentPlayerIdAndOutcome(Long opponentPlayerId, Outcome outcome);

    List<Match> findTop10ByOpponentPlayerIdOrderByMatchDateDescIdDesc(Long playerId);

    WinLoseStatsView aggregate(Class<? extends Enum<?>> statType, DashboardPeriod period);
}
