package developx.langrisser.api.request;

import developx.langrisser.match.*;
import developx.langrisser.user.User;

import java.time.LocalDateTime;

public record MatchCreateRequest(
        LocalDateTime matchedDate,
        Stage stage,
        WinOrLose winOrLose,
        Orders orders,
        MatchType matchType,
        Long userId
) {
    public Match toEntity(User user) {
        return Match.builder()
                .matchedDateTime(matchedDate)
                .matchType(matchType)
                .orders(orders)
                .stage(stage)
                .user(user)
                .winOrLose(winOrLose)
                .build();
    }
}