package developx.langrisser.match.service.dto;

import developx.langrisser.match.Match;
import developx.langrisser.user.User;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record MatchesData (
        Long matchId,
        String stage,
        String winOrLose,
        String orders,
        String matchType,
        String comment,
        String matchedDateTime,

        Long userId,
        String userName,
        String server
) {
    public static MatchesData fromMatch(Match match) {

        User user = match.getUser();
        return MatchesData.builder()
                // 게임정보
                .matchId(match.getId())
                .stage(match.getStage().getText())
                .winOrLose(match.getWinOrLose().name())
                .orders(match.getOrders().getText())
                .matchType(match.getMatchType().getText())
                .comment(match.getComment())
                .matchedDateTime(match.getMatchedDateTime().format(DateTimeFormatter.ISO_DATE_TIME))

                // 상대 정보
                .userName(user.getName())
                .userId(user.getId())
                .server(user.getServer().getText())
                .build();
    }

}
