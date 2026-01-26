package developx.langrisser.domain.player;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerNicknameHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    private LocalDateTime createdAt;

    private PlayerNicknameHistory(Player player, String nickname) {
        this.player = player;
        this.nickname = nickname;
        this.createdAt = LocalDateTime.now();
    }

    public static PlayerNicknameHistory of(Player player, String nickname) {
        return new PlayerNicknameHistory(player, nickname);
    }
}