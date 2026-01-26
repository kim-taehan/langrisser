package developx.langrisser.domain.player;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;


class PlayerTest {

    private static final Pattern SPECIAL_NICKNAME_PATTERN =
            Pattern.compile(".*[^가-힣a-zA-Z].*");

    private static boolean containsSpecialChar(String nickname) {
        return SPECIAL_NICKNAME_PATTERN.matcher(nickname).matches();
    }

    @Test
    void test() {


        boolean a = containsSpecialChar("Ж");
        boolean b = containsSpecialChar("한글");


        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}