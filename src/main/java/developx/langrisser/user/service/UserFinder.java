package developx.langrisser.user.service;

import developx.langrisser.user.User;
import developx.langrisser.user.UserRepository;
import developx.langrisser.web.request.UserFindRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFinder {

    public static final String PERCENT = "%";
    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<User> findUsers(UserFindRequest request) {
        return userRepository.findByNameLikeAndServer(PERCENT + request.userName() + PERCENT, request.server());
    }
}
