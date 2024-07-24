package developx.langrisser.match.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import developx.langrisser.infrastructure.querydsl.Querydsl4RepositorySupport;
import developx.langrisser.match.Match;
import developx.langrisser.user.Server;
import developx.langrisser.user.User;
import developx.langrisser.user.repository.UserQueryDslRepository;
import developx.langrisser.web.request.MatchesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static developx.langrisser.user.QUser.user;

public class MatchRepositoryImpl extends Querydsl4RepositorySupport implements MatchQueryDslRepository {
    public MatchRepositoryImpl() {
        super(Match.class);
    }

    private BooleanExpression userNameLike(String serverName) {
        return serverName != null ? user.name.startsWith(serverName) : null;
    }

    private BooleanExpression serverTypeEq(Server server) {
        return (server == null || Server.UNDEFINED.equals(server)) ? null : user.server.eq(server)  ;
    }

    @Override
    public List<Match> findByCond(MatchesRequest request) {
        return null;
    }
}
