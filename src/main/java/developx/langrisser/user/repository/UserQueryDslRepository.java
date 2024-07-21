package developx.langrisser.user.repository;

import developx.langrisser.user.Server;
import developx.langrisser.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserQueryDslRepository {

    Page<User> findByUserNameNameLikeAndServer(String userName, Server server, Pageable pageable);
}
