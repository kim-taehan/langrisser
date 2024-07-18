package developx.langrisser.match;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stage {

    SHEER_CLIFF("깎아지른듯한절벽"),
    SNOW_FIELD("눈덮인벌판"),
    MOUNTAIN_TEARS("눈물의산길"),
    PLAINS_DISPUTE("분쟁의평원"),
    DESERT_EYES("사막의눈"),
    SEOPUNG_POST("서풍초소"),
    TWIN_BRIDGE("트윈브릿지의파도"),
    SKY_GARDEN("하늘정원"),
    MOUNTAIN_STREAM("비경의계류"),
    LIGHT_CAVE("빛이비치는동굴"),
    BROKEN_SHIP("망가진함선의길목"),
    FLOWS_LAVA("용암이 흐르는대지의_심장");

    private final String mapName;

}
