package developx.langrisser.adapter.persistence;


import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.domain.match.Match;
import developx.langrisser.domain.match.Outcome;

import java.util.List;

public interface MatchRepositoryCustom {

    default List<Match> findAll(MatchSearchQuery request) {
       return findAll(request, 100);
    }

    List<Match> findAll(MatchSearchQuery request, int limit);

    Long countMatches(MatchSearchQuery request, Outcome outcome);

}
