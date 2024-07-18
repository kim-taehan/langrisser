package developx.langrisser.web;

import developx.langrisser.match.Stage;
import developx.langrisser.match.WinOrLose;
import developx.langrisser.match.service.MatchCreator;
import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchCreator matchCreator;
    private final UserFinder userFinder;

    @Transactional
    @GetMapping("{userId}")
    public String proposals(Model model, @PathVariable(name = "userId") Long userId) {

        User user = userFinder.findById(userId);
        model.addAttribute("user", user);

        // username like 검색으로 조회합니다.
        return "match/match";
    }

    /**
     * enum
     */
    @ModelAttribute("stages")
    public Stage[] stages() {
        return Stage.values();
    }

    @ModelAttribute("winOrLoses")
    public WinOrLose[] winOrLoses() {
        return WinOrLose.values();
    }
}
