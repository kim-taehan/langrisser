package developx.langrisser.web;

import developx.langrisser.user.Server;
import developx.langrisser.user.User;
import developx.langrisser.user.service.UserFinder;
import developx.langrisser.web.request.UserFindRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFinder userFinder;

    @GetMapping("")
    public String findUsers(Model model, @ModelAttribute UserFindRequest request) {

        // 검색 필터를 내려줘야 한다.
        if (request.server() == null) {
            request = UserFindRequest.initData();
        }
        model.addAttribute("userFinder", request);

        // username like 검색으로 조회합니다.
        Page<User> users = userFinder.findUsers(request);
        model.addAttribute("users", users);
        return "user/users";
    }

    @ModelAttribute("servers")
    public Server[] servers() {
        return Server.values();
    }
}
