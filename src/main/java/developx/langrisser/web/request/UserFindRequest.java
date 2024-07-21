package developx.langrisser.web.request;

import developx.langrisser.user.Server;
import lombok.Builder;

@Builder
public record UserFindRequest(String userName, Server server) {
    public static UserFindRequest initData() {
        return UserFindRequest.builder()
                .server(Server.UNDEFINED)
                .userName("")
                .build();
    }


}
