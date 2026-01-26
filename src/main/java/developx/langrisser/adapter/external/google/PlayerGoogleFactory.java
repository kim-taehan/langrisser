package developx.langrisser.adapter.external.google;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlayerGoogleFactory {

    public PlayerGoogle create(String[] values, Map<String, Integer> indexMap) {
        return PlayerGoogle.builder()
                .nickname(values[indexMap.get("name")].trim())
                .server(values[indexMap.get("server")].trim())
                .point(values[indexMap.get("point")].trim())
                .pointRank(values[indexMap.get("rank(p)")].trim())
                .cristal(values[indexMap.get("cristal")].trim())
                .cristalRank(values[indexMap.get("rank(c)")].trim())
                .oldNickname(Arrays.stream(values[indexMap.get("이전 아이디")].split(",")).map(String::trim).toList())
                .tiers(parseSeasons(values, indexMap))
                .build();
    }


    private Map<String, String> parseSeasons(
            String[] values,
            Map<String, Integer> indexMap
    ) {
        Map<String, String> tireMap = new HashMap<>();
        for (int i = 1; ; i++) {
            String sKey = "s" + i;
            if (!indexMap.containsKey(sKey)) {
                break; // 더 이상 시즌 없음
            }
            tireMap.put(sKey, values[indexMap.get(sKey)].trim());
        }
        return tireMap;
    }
}
