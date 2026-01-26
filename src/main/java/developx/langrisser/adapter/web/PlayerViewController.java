package developx.langrisser.adapter.web;

import developx.langrisser.application.match.dto.MatchSearchQuery;
import developx.langrisser.application.match.dto.MatchSummaryView;
import developx.langrisser.application.match.dto.RegisterMatchCommand;
import developx.langrisser.application.match.provided.MatchFinder;
import developx.langrisser.application.player.dto.FindPlayerQuery;
import developx.langrisser.application.player.dto.PlayerDetailView;
import developx.langrisser.application.player.dto.PlayerStatsSummaryView;
import developx.langrisser.application.player.dto.RegisterPlayerCommand;
import developx.langrisser.application.player.provided.PlayerFinder;
import developx.langrisser.application.player.provided.PlayerRegister;
import developx.langrisser.application.snapshot.provided.SyncSnapshotFinder;
import developx.langrisser.application.snapshot.required.SyncSnapshotStore;
import developx.langrisser.domain.match.GameMode;
import developx.langrisser.domain.match.Stage;
import developx.langrisser.domain.match.TurnOrder;
import developx.langrisser.domain.player.ServerType;
import developx.langrisser.domain.snapshot.SyncSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerViewController {

    private final PlayerRegister register;

    private final PlayerFinder playerFinder;

    private final MatchFinder matchFinder;

    private final SyncSnapshotFinder snapshotFinder;

    @GetMapping
    public String players(
            @ModelAttribute("query") FindPlayerQuery query,
            Model model
    ) {
        model.addAttribute("command", RegisterPlayerCommand.init());
        model.addAttribute("players", playerFinder.findPlayers(query));
        model.addAttribute("snapshotLatestDate", snapshotFinder.findLatestDate());
        return "player/players";
    }


    @GetMapping("/new")
    public String newPlayerForm(Model model) {
        model.addAttribute("command", RegisterPlayerCommand.init());
        return "player/new";
    }

    @PostMapping
    public String register(
            @ModelAttribute("command") RegisterPlayerCommand command,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "player/new";
        }

        register.register(command);
        return "redirect:/players";
    }

    @GetMapping("/{playerId}")
    public String detail(
            @PathVariable Long playerId,
            Model model,
            @ModelAttribute("query") MatchSearchQuery query

    ) {
        MatchSearchQuery updatedQuery = query.fromPlayerId(playerId);
        model.addAttribute("stages", Stage.values());
        model.addAttribute("turnOrders", TurnOrder.values());
        model.addAttribute("command", RegisterMatchCommand.init(playerId));
        model.addAttribute("gameModes", GameMode.values());
        PlayerDetailView player = playerFinder.findPlayer(playerId);
        model.addAttribute("player", player);
        PlayerStatsSummaryView state = matchFinder.findHistory(updatedQuery);
        model.addAttribute("state", state);
        List<MatchSummaryView> matches = matchFinder.findMatches(updatedQuery);
        model.addAttribute("matches", matches);
        return "player/player";
    }

    @ModelAttribute("servers")
    public ServerType[] servers() {
        return ServerType.values();
    }

    @PostMapping("/nickname")
    public String changeNickname(
            @RequestParam Long playerId,
            @RequestParam String newNickname,
            @ModelAttribute("query") FindPlayerQuery query,
            RedirectAttributes redirectAttributes
    ) {
        register.changeNickname(playerId, newNickname);
        redirectAttributes.addAttribute("nickname", query.nickname());
        redirectAttributes.addAttribute("server", query.server());
        return "redirect:/players";
    }
}
