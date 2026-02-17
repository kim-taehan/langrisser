package developx.langrisser.adapter.web;

import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.RegisterMatchCommand;
import developx.langrisser.application.match.provided.MatchFinder;
import developx.langrisser.application.match.provided.MatchRegister;
import developx.langrisser.application.player.provided.PlayerFinder;
import developx.langrisser.domain.match.GameMode;
import developx.langrisser.domain.match.Outcome;
import developx.langrisser.domain.match.Stage;
import developx.langrisser.domain.match.TurnOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchViewController {

    private final PlayerFinder playerFinder;
    private final MatchFinder matchFinder;

    @GetMapping("/new")
    public String newMatchForm(
            @RequestParam Long playerId,
            Model model
    ) {
        model.addAttribute("command", RegisterMatchCommand.init(playerId));
        model.addAttribute("opponent", playerFinder.findPlayer(playerId));
        return "match/new";
    }

    @GetMapping
    public String matches(
            @ModelAttribute("query") MatchSearchQuery query,
            Model model
    ) {
        model.addAttribute("matches", matchFinder.findMatches(query));
        model.addAttribute("state", matchFinder.findHistory(query));
        model.addAttribute("gameModes", GameMode.values());
        model.addAttribute("stages", Stage.values());
        model.addAttribute("turnOrders", TurnOrder.values());
        return "match/matches";
    }

    @ModelAttribute("gameModes")
    public GameMode[] gameModes() {
        return GameMode.values();
    }

    @ModelAttribute("stages")
    public Stage[] stages() {
        return Stage.values();
    }

    @ModelAttribute("outcomes")
    public Outcome[] outcomes() {
        return Outcome.values();
    }

    @ModelAttribute("turnOrders")
    public TurnOrder[] turnOrders() {
        return TurnOrder.values();
    }

}
