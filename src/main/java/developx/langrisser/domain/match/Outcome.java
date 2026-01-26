package developx.langrisser.domain.match;

import developx.langrisser.domain.LabeledEnum;

public enum Outcome implements LabeledEnum {

    WIN,
    LOSE;

    @Override
    public String label() {
        return name();
    }
}