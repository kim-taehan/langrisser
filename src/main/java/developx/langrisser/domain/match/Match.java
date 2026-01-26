package developx.langrisser.domain.match;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "match_result")
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opponent_player_id", nullable = false)
    private Long opponentPlayerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private Outcome outcome;

    @Enumerated(EnumType.STRING)
    @Column(name = "turn_order", nullable = false, length = 10)
    private TurnOrder turnOrder;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Stage stage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private GameMode mode;

    @Column(length = 30)
    private String memo;

    public void updateMemo(String memo) {
        this.memo = memo;
    }

}
