package developx.langrisser.domain.player;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MetricRank {

    @Column(nullable = false)
    private int value;

    @Column
    private Integer rank;

    private MetricRank(int value, Integer rank) {
        this.value = value;
        this.rank = rank;
    }

    public static MetricRank of(String value, String rank) {
        return new MetricRank(Integer.parseInt(value.replace(",", "")), Integer.parseInt(rank));
    }

    public static MetricRank initial() {
        return new MetricRank(0, null);
    }

    public void add(int amount) {
        this.value += amount;
    }

    public void updateValue(int value) {
        this.value = value;
    }

    public void updateRank(Integer rank) {
        this.rank = rank;
    }
}
