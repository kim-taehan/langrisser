package developx.langrisser.adapter.persistence.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import developx.langrisser.adapter.persistence.MatchRepositoryCustom;
import developx.langrisser.application.dashboard.DashboardPeriod;
import developx.langrisser.application.dashboard.dto.LabeledEnumStat;
import developx.langrisser.application.dashboard.dto.WinLoseCount;
import developx.langrisser.application.dashboard.dto.WinLoseStatsView;
import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.domain.LabeledEnum;
import developx.langrisser.domain.match.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static developx.langrisser.domain.match.QMatch.match;

@Repository
@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    public WinLoseStatsView aggregate(
            Class<? extends Enum<?>> statType,
            DashboardPeriod period
    ) {
        QMatch m = QMatch.match;
        EnumPath<? extends Enum<?>> groupPath =
                getType(statType).orElseThrow(() -> new IllegalArgumentException("Unknown stat type: " + statType));

        List<Tuple> rows = queryFactory
                .select(
                        groupPath,
                        m.outcome.when(Outcome.WIN).then(1L).otherwise(0L).sum(),
                        m.outcome.when(Outcome.LOSE).then(1L).otherwise(0L).sum()
                )
                .from(m)
                .where(
                        period.fromDate() == null ? null :
                        m.matchDate.goe(period.fromDate())
                )
                .groupBy(groupPath)
                .fetch();

        return new WinLoseStatsView(
                rows.stream()
                        .map(t -> LabeledEnumStat.from(
                                (LabeledEnum) t.get(groupPath),
                                new WinLoseCount(
                                        t.get(1, Long.class),
                                        t.get(2, Long.class)
                                )
                        ))
                        .toList()
        );
    }

    public Optional<EnumPath<? extends Enum<?>>> getType(Class<?> type) {
        if (type == Stage.class) return Optional.of(match.stage);
        if (type == TurnOrder.class) return Optional.of(match.turnOrder);
        if (type == GameMode.class) return Optional.of(match.mode);
        return Optional.empty();
    }

    @Override
    public List<Match> findAll(MatchSearchQuery request, int limit) {
        return queryFactory
                .selectFrom(match)
                .where(
                        gameModeEq(request.gameMode()),
                        stageEq(request.stage()),
                        turnOrderEq(request.turnOrder()),
                        opponentPlayerIdEq(request.opponentPlayerId())
                )
                .orderBy(match.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public Long countMatches(MatchSearchQuery request, Outcome outcome) {
        return queryFactory
                .select(match.count())
                .from(match)
                .where(
                        gameModeEq(request.gameMode()),
                        stageEq(request.stage()),
                        turnOrderEq(request.turnOrder()),
                        opponentPlayerIdEq(request.opponentPlayerId()),
                        match.outcome.eq(outcome)
                )
                .fetchOne();
    }


    private BooleanExpression gameModeEq(GameMode gameMode) {
        return gameMode != null
                ? match.mode.eq(gameMode)
                : null;
    }

    private BooleanExpression stageEq(Stage stage) {
        return stage != null
                ? match.stage.eq(stage)
                : null;
    }

    private BooleanExpression turnOrderEq(TurnOrder turnOrder) {
        return turnOrder != null
                ? match.turnOrder.eq(turnOrder)
                : null;
    }

    private BooleanExpression opponentPlayerIdEq(Long opponentPlayerId) {
        return opponentPlayerId != null && opponentPlayerId > 0L
                ? match.opponentPlayerId.eq(opponentPlayerId)
                : null;
    }

}
