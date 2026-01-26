package developx.langrisser.application.match;

import developx.langrisser.application.match.dto.MatchDetailView;
import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.MatchSummaryView;
import developx.langrisser.application.match.dto.PlayerMatchStats;
import developx.langrisser.application.match.provided.MatchFinder;
import developx.langrisser.application.match.required.MatchStore;
import developx.langrisser.application.player.dto.PlayerStatsSummaryView;
import developx.langrisser.application.player.required.PlayerStore;
import developx.langrisser.domain.match.Match;
import developx.langrisser.domain.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchQueryService implements MatchFinder {

    private final MatchStore matchStore;
    private final PlayerStore playerStore;

    @Override
    public MatchDetailView findMatch(Long matchId) {
        Match match = matchStore.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match not found: " + matchId));
        return MatchDetailView.from(match, getPlayerDetailView(match));
    }

    @Override
    public List<MatchSummaryView> findMatches(MatchSearchQuery request) {
        return matchStore.findAll(request).stream()
                .map(match -> MatchSummaryView.from(match, getPlayerDetailView(match)))
                .toList();
    }

//    @Override
//    public PlayerStatsSummaryView findStateByPlayerId(Long playerId) {
//        PlayerMatchStats playerMatchStats = matchStore.loadPlayerStats(playerId);
//        return PlayerStatsSummaryView.from(playerMatchStats);
//    }

    @Override
    public PlayerStatsSummaryView findHistory(MatchSearchQuery updatedQuery) {
        PlayerMatchStats playerMatchStats = matchStore.loadHistory(updatedQuery);
        return PlayerStatsSummaryView.from(playerMatchStats);
    }

    private Player getPlayerDetailView(Match match) {
        return playerStore.findById(match.getOpponentPlayerId())
                .orElseThrow(() -> new IllegalArgumentException("player not found: " + match.getOpponentPlayerId()));
    }


}
