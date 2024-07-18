package developx.langrisser.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    @GetMapping("")
    public String proposals(Model model) {
        return "portfolio/portfolio";
    }
}
