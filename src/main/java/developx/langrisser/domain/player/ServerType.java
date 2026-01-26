package developx.langrisser.domain.player;

import developx.langrisser.domain.LabeledEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ServerType implements LabeledEnum {

    TISRAO_CASTLE("티스라오"),
    KARMEN_CASTLE("카먼"),
    NOSENREYER_CASTLE("노센레이어"),
    RAYGARD_EMPIRE("레이갈드"),
    KARZAS_KINGDOM("칼자스"),
    VALDIA_KINGDOM("발디아"),
    BELZERIA("벨제리아"),
    HOLY_SWORD_LEGION( "성검"),
    LIGRIA_EMPIRE( "리그리아"),
    RAKAS_KINGDOM( "라카스"),
    DARTHYS_EMPIRE("다르시스"),
    KAKONSIS_KINGDOM( "카콘시스"),
    HEIRS_OF_LIGHT("빛의후예"),
    LEGEND_OF_DARKNESS( "어둠의전설"),
    ISKANOR_FORTRESS( "이스카노"),
    REGENBURG( "레겐부르그"),
    ELSRID_KINGDOM( "엘스리드"),
    KOLSIA_KINGDOM( "콜시아"),
    PERSEUS_ISLANDS("페르세우스"),
    HYPERBOREAN_EMPIRE("하이보레안"),
    IVAS_FEDERATION("이바스"),
    BORSRENU( "보르스레누"),
    IRIS_CASTLE( "이리스성"),
    TSURUYA_SHRINE("츠루야"),
    NORAM_SNOWFIELD( "노람");

    private final String label;

    ServerType(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static ServerType fromLabel(String label) {
        if (label == null) {
            return null;
        }

        for (ServerType type : ServerType.values()) {
            if (type.label.contains(label)) {
                return type;
            }
        }
        return null;
    }
}