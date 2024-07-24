package developx.langrisser.match.repository;

import developx.langrisser.match.Match;
import developx.langrisser.web.request.MatchesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchQueryDslRepository {
    Page<Match> findByCond(MatchesRequest request, Pageable pageable);
}
