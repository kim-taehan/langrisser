package developx.langrisser.adapter.web;

import developx.langrisser.application.player.dto.PlayerCommentRequest;
import developx.langrisser.application.player.dto.PlayerDetailView;
import developx.langrisser.application.player.provided.PlayerFinder;
import developx.langrisser.application.player.provided.PlayerRegister;
import developx.langrisser.application.snapshot.dto.SyncSnapshotDto;
import developx.langrisser.application.snapshot.provided.SyncSnapshotFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerQueryController {

    private final PlayerFinder finder;

    private final PlayerRegister register;

    private final SyncSnapshotFinder snapshotFinder;

    @GetMapping("/{id}/detail")
    public PlayerDetailView detail(@PathVariable Long id) {
        return finder.findPlayer(id);
    }

    @PatchMapping("/{id}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(
            @PathVariable Long id,
            @RequestBody PlayerCommentRequest request
    ) {
        register.updateComment(id, request.comment());
    }

    @PostMapping("/snapshot")
    public void updateSyncSnapshot(@RequestBody SyncSnapshotDto request) {
        snapshotFinder.update(request);
    }

}