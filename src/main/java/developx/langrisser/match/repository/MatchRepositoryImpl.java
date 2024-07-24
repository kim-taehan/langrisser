package developx.langrisser.match.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import developx.langrisser.infrastructure.querydsl.Querydsl4RepositorySupport;
import developx.langrisser.match.Match;
import developx.langrisser.match.MatchType;
import developx.langrisser.match.Stage;
import developx.langrisser.web.request.MatchesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static developx.langrisser.match.QMatch.match;

public class MatchRepositoryImpl extends Querydsl4RepositorySupport implements MatchQueryDslRepository {
    public MatchRepositoryImpl() {
        super(Match.class);
    }

    private BooleanExpression matchTypeEq(MatchType matchType) {
        return (matchType == null || MatchType.NONE.equals(matchType)) ? null : match.matchType.eq(matchType)  ;
    }
    private BooleanExpression stageEq(Stage stage) {
        return (stage == null || Stage.NONE.equals(stage)) ? null : match.stage.eq(stage)  ;
    }

    @Override
    public Page<Match> findByCond(MatchesRequest request, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(match)
                        .where(
                                matchTypeEq(request.matchType())
                                , matchTypeEq(request.matchType())
                        )
        );
    }
}
