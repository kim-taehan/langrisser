package developx.langrisser.adapter.web;

import developx.langrisser.application.match.dto.RegisterMatchCommand;
import developx.langrisser.application.match.provided.MatchRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchCommandController {
    private final MatchRegister register;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(
            @ModelAttribute("command") RegisterMatchCommand command,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("invalid form");
        }

        register.register(command);
    }

    @DeleteMapping("{matchId}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long matchId) {
        register.remove(matchId);
    }
}
