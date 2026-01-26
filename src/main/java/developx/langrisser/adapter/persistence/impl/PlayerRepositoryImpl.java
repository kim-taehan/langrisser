package developx.langrisser.adapter.persistence.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import developx.langrisser.adapter.persistence.PlayerRepositoryCustom;
import developx.langrisser.application.player.dto.FindPlayerQuery;
import developx.langrisser.domain.player.Player;
import developx.langrisser.domain.player.QPlayer;
import developx.langrisser.domain.player.ServerType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static developx.langrisser.domain.player.QPlayer.player;

@Repository
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Player> findAll(FindPlayerQuery query) {
        QPlayer player = QPlayer.player;

        return queryFactory
                .selectFrom(player)
                .where(
                        nicknameLike(query.nickname()),
                        serverEq(query.server()),
                        specialOnly(query.specialOnly())


                )
                .orderBy(player.summit.value.desc(), player.crystal.value.desc())
                .fetch();
    }

    @Override
    public List<Player> findCandidates(ServerType server, String nickname, List<String> oldNicknames) {

        QPlayer player = QPlayer.player;

        return queryFactory
                .selectFrom(player)
                .leftJoin(player.summitRanks).fetchJoin()
                .where(
                        serverEq(server),
                        nicknameSame(nickname, oldNicknames)
                )
                .fetch();
    }

    private BooleanExpression nicknameLike(String nickname) {
        return StringUtils.hasText(nickname)
                ? player.nickname.likeIgnoreCase("%"+nickname+"%")
                : null;
    }

    private BooleanExpression nicknameSame(String nickname, List<String> oldNicknames) {
        // 트윈테일츤데레
        BooleanExpression currentNickname =
                StringUtils.hasText(nickname)
                        ? player.nickname.eq(nickname)
                        : null;

        BooleanExpression oldNicknameCondition =
                (oldNicknames != null && !oldNicknames.isEmpty())
                        ? player.nickname.in(oldNicknames)
                        : null;

        if (currentNickname == null) {
            return oldNicknameCondition;
        }

        if (oldNicknameCondition == null) {
            return currentNickname;
        }

        return currentNickname.or(oldNicknameCondition);

    }

    private BooleanExpression serverEq(ServerType server) {
        return server != null
                ? player.server.eq(server)
                : null;
    }

    private BooleanExpression specialOnly(Boolean specialOnly) {
        return specialOnly != null && specialOnly ? player.hasSpecialNickname.isTrue() : null;
    }
}