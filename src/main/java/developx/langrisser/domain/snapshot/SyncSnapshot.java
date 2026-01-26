package developx.langrisser.domain.snapshot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Slf4j
@Builder
public class SyncSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    public static SyncSnapshot create() {
        return SyncSnapshot.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }
}
