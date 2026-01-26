package developx.langrisser.domain.player;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "player_summit_rank",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_player_season",
                        columnNames = {"player_id", "season"}
                )
        }
)
public class PlayerSummitRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(nullable = false, length = 10)
    private String season;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SeasonTier tier;

    public static PlayerSummitRank of(Player player, String season, SeasonTier tier) {


        PlayerSummitRank playerSummitRank = new PlayerSummitRank();
        playerSummitRank.player = player;
        playerSummitRank.season = season;
        playerSummitRank.tier = tier;
        return playerSummitRank;
    }
}
