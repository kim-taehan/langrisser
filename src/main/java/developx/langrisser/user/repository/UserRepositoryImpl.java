package developx.langrisser.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import developx.langrisser.infrastructure.querydsl.Querydsl4RepositorySupport;
import developx.langrisser.user.Server;
import developx.langrisser.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static developx.langrisser.user.QUser.user;

public class UserRepositoryImpl extends Querydsl4RepositorySupport implements UserQueryDslRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Page<User> findByUserNameNameLikeAndServer(String userName, Server server, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery
                        .selectFrom(user)
                        .where(
                                userNameLike(userName)
                                , serverTypeEq(server)

                        )
        );
    }

    private BooleanExpression userNameLike(String serverName) {
        return serverName != null ? user.name.startsWith(serverName) : null;
    }

    private BooleanExpression serverTypeEq(Server server) {
        return (server == null || Server.UNDEFINED.equals(server)) ? null : user.server.eq(server)  ;
    }
}
