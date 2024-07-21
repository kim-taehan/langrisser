package developx.langrisser.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum Server {
    UNDEFINED("미정"),
    TISRAO("티스라오"),
    KAMON("카먼"),
    NOSENREYER("노센레이어"),
    RAYGARD("레이갈드"),
    CALZAS("칼자스"),
    BALDIA("발디아"),
    BELZERIA("벨제리아"),
    HOLY_SWORD("성검"),
    LIGLIA("리그리아"),
    LAKAS("라카스"),
    DARCIS("다르시스"),
    CACONSIS("카콘시스"),
    DESCENDANTS_LIGHT("빛의후예"),
    LEGEND_DARKNESS("어둠의전설"),
    ISCANO("이스카노"),
    REGENBURG("레겐부르그"),
    ELSRID("엘스리드"),
    TÜRKIYE("콜시아"),
    PELSEUS("페르세우스"),
    HYBOREAN("하이보레안"),
    IBAS("이바스"),
    BORSRENU("보르스레누"),
    IRIS("이리스"),
    TSURUYA("츠루야");

    private final String text;

    private final static Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(Server.values()).collect(Collectors.toMap(Server::getText, Server::name))
    );

    public static Server of(final String value) {
        return Server.valueOf(CODE_MAP.getOrDefault(value, String.valueOf(UNDEFINED)));
    }


}
