package developx.langrisser.web;

import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserFinder userFinder;
    @GetMapping("")
    public String proposals(Model model, String userName) {

        // username like 검색으로 조회합니다.
        List<User> users = userFinder.findLikeUserName(userName);
        model.addAttribute("users", users);
        return "user/user";
    }
}
