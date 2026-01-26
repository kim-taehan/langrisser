package developx.langrisser.domain.match;

import developx.langrisser.domain.LabeledEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Stage implements LabeledEnum {

    CLIFF("깎아 지른듯한 절벽", "⛰️"),
    SNOW_FIELD("눈 덮인 벌판", "❄️"),
    TEAR_MOUNTAIN("눈물의 산길", "💧"),
    CONFLICT_PLAINS("분쟁의 평원", "⚔️"),
    DESERT_EYE("사막의 눈", "🏜️"),
    WEST_WATCHPOST("서풍 초소", "🏯"),
    TWIN_BRIDGE_WAVES("트윈브릿지의 파도", "🌊"),
    SKY_GARDEN("하늘 정원", "🌿"),
    SECRET_STREAM("비경의 계류", "✨"),
    SHINING_CAVE("빛이 비치는 동굴", "🪨"),
    BROKEN_SHIP_ROUTE("망가진 함선의 길목", "🚢"),
    LAVA_HEART("유황이 녹는 땅", "🌋"),
    CIRCULAR_HILL("순환 언덕", "♻️"),
    FLOATING_SECRET_REALM("부유 비경", "🌀");


    private final String label;
    private final String emoji;

    public String displayName() {
        return emoji + " " + label;
    }

    @Override
    public String label() {
        return label;
    }
}
