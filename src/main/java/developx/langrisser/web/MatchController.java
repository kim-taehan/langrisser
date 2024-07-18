package developx.langrisser.web;

import developx.langrisser.match.service.MatchCreator;
import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchCreator matchCreator;

    @GetMapping("{userId}")
    public String proposals(Model model, @PathVariable(name = "userId") Long userId) {

        // username like 검색으로 조회합니다.
        return "match/match";
    }
}
