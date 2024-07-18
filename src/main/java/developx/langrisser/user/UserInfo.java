package developx.langrisser.user;

import developx.langrisser.infrastructure.csv.CsvHeader;
import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Builder
public record UserInfo(
        String pointRank,
        String point,
        String cristalRank,
        String cristal,
        String name,
        String server,
        List<String> beforeIds
) {

    public static UserInfo from(HashMap<String, Integer> headers, String[] nextRecord) {
        return UserInfo.builder()
                .pointRank(getField(headers, nextRecord, CsvHeader.RANK_POINT))
                .point(getField(headers, nextRecord, CsvHeader.POINT))
                .cristalRank(getField(headers, nextRecord, CsvHeader.RANK_CRISTAL))
                .cristal(getField(headers, nextRecord, CsvHeader.CRISTAL))
                .name(getField(headers, nextRecord, CsvHeader.NAME))
                .server(getField(headers, nextRecord, CsvHeader.SERVER))
                .beforeIds(extractBeforeIds(getField(headers, nextRecord, CsvHeader.BEFORE_IDS)))
                .build();
    }

    private static String getField(HashMap<String, Integer> headers, String[] nextRecord, CsvHeader csvHeader) {
        String key = csvHeader.getKey();
        return headers.containsKey(key)
                ? nextRecord[headers.get(key)]
                : "";
    }

    private static List<String> extractBeforeIds(String ids) {
        return StringUtils.hasText(ids)
                ? Arrays.stream(ids.split(",")).map(s -> s.replaceAll(" ", "")).toList()
                : Collections.EMPTY_LIST;
    }

    public User toEntity(){

        return User.builder()
                .name(name)
                .server(Server.of(server))
                .cristal(parseInt(cristal))
                .cristalRank(parseInt(cristalRank))
                .pointRank(parseInt(pointRank))
                .point(parseInt(point))
                .beforeNames(beforeIds)
                .build();
    }

    public int parseInt(String numberText) {
        return StringUtils.hasText(numberText)
                ? Integer.parseInt(numberText.replaceAll(",", ""))
                : 0;
    }

}