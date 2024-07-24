package developx.langrisser.match.repository;

import developx.langrisser.match.Match;
import developx.langrisser.web.request.MatchesRequest;

import java.util.List;

public interface MatchQueryDslRepository {
    List<Match> findByCond(MatchesRequest request);
}
