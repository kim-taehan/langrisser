package developx.langrisser.adapter.persistence;

import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.WinLoseStatsView;
import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.PlayerMatchStats;
import developx.langrisser.application.match.required.MatchStore;
import developx.langrisser.domain.match.Match;
import developx.langrisser.domain.match.Outcome;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaMatchStore implements MatchStore {

    private final MatchRepository repository;

    @Override
    public Match save(Match match) {
        return repository.save(match);
    }

    @Override
    public Optional<Match> findById(Long matchId) {
        return repository.findById(matchId);
    }

    @Override
    public List<Match> findAll(MatchSearchQuery request) {
        return repository.findAll(request);
    }

    public void remove(Match match) {
        repository.delete(match);
    }

    @Override
    public PlayerMatchStats loadHistory(MatchSearchQuery updatedQuery) {

        Long totalWin = repository.countMatches(updatedQuery, Outcome.WIN);
        Long totalLose = repository.countMatches(updatedQuery, Outcome.LOSE);

        List<Match> recent = repository.findAll(updatedQuery, 10);

        int recentWin = (int) recent.stream()
                .filter(m -> m.getOutcome() == Outcome.WIN)
                .count();

        int recentLose = recent.size() - recentWin;
        return PlayerMatchStats.builder()
                .totalWin(totalWin == null ? 0 : totalWin.intValue())
                .totalLose(totalLose == null ? 0 : totalLose.intValue())
                .recentWin(recentWin)
                .recentLose(recentLose)
                .build();
    }

    @Override
    public WinLoseStatsView aggregate(Class<? extends Enum<?>> statType, DashboardPeriod period) {
        return repository.aggregate(statType, period);
    }
}
