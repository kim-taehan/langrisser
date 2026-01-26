package developx.langrisser.application.match.provided;

import developx.langrisser.application.match.dto.MatchDetailView;
import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.MatchSummaryView;
import developx.langrisser.application.player.dto.PlayerStatsSummaryView;

import java.util.List;

public interface MatchFinder {

    MatchDetailView findMatch(Long matchId);

    List<MatchSummaryView> findMatches(MatchSearchQuery request);

    PlayerStatsSummaryView findHistory(MatchSearchQuery updatedQuery);
}