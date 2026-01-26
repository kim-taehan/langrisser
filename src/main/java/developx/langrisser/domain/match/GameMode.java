package developx.langrisser.domain.match;

import developx.langrisser.domain.LabeledEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameMode implements LabeledEnum {

    CASUAL("캐주얼", "🎮"),
    RANKED("랭킹전", "🥇"),
    TOURNAMENT("토너먼트", "🏆"),
    PRIVATE_TOURNAMENT("사설대회", "🛠️"),
    DUEL("겨루기", "🥊");

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
