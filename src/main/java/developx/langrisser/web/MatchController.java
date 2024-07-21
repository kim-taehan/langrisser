package developx.langrisser.web;

import developx.langrisser.match.MatchType;
import developx.langrisser.match.Orders;
import developx.langrisser.match.Stage;
import developx.langrisser.match.WinOrLose;
import developx.langrisser.match.service.MatchCreator;
import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import developx.langrisser.web.request.MatchRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/matches")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchController {

    private final MatchCreator matchCreator;
    private final UserFinder userFinder;

    @GetMapping("{userId}")
    public String proposals(Model model, @PathVariable(name = "userId") Long userId) {
        User user = userFinder.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("match", MatchRegisterRequest.initData());
        // username like 검색으로 조회합니다.
        return "match/match";
    }

    @PostMapping("{winOrLose}/{userId}")
    public String register(
            Model model,
            @PathVariable(name = "winOrLose") WinOrLose winOrLose,
            @PathVariable(name = "userId") Long userId,
            MatchRegisterRequest request
    ) {
        matchCreator.register(userId, request.toEntity(winOrLose));
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

    @ModelAttribute("orders")
    public Orders[] orders() {
        return Orders.values();
    }

    @ModelAttribute("matchTypes")
    public MatchType[] matchType() {
        return MatchType.values();
    }
}
