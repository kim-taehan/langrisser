package developx.langrisser.web.request;

import developx.langrisser.user.Server;
import lombok.Builder;

@Builder
public record UsersRequest(String userName, Server server) {
    public static UsersRequest initData() {
        return UsersRequest.builder()
                .server(Server.UNDEFINED)
                .userName("")
                .build();
    }
}
