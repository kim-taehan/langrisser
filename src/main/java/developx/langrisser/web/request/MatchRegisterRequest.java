package developx.langrisser.web.request;

import developx.langrisser.match.*;
import developx.langrisser.user.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MatchRegisterRequest(MatchType matchType, Orders orders, Stage stage, WinOrLose winOrLose, String comment) {
    public static MatchRegisterRequest initData() {
        return MatchRegisterRequest.builder()

                .matchType(MatchType.NONE)
                .orders(Orders.NONE)
                .stage(Stage.NONE)
                .comment("")
                .build();
    }

    public Match toEntity(WinOrLose winOrLose) {

        return Match.builder()
                .matchType(matchType)
                .orders(orders)
                .stage(stage)
                .winOrLose(winOrLose)
                .comment(comment)
                .matchedDateTime(LocalDateTime.now())
                .build();
    }
}
