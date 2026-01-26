package developx.langrisser.adapter.external.google;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerGoogleSheetLoader {

    private final PlayerGoogleFactory factory;

    public List<PlayerGoogle> load(String csvUrl) {

        List<PlayerGoogle> players = new ArrayList<>();

        try (
                CSVReader reader = new CSVReader(new InputStreamReader(new URL(csvUrl).openStream(), StandardCharsets.UTF_8))
            ) {
            Map<String, Integer> headers = parseHeader(reader.readNext());

            String[] line;
            while ((line = reader.readNext()) != null) {
                players.add(factory.create(line, headers));
            }
            return players;

        } catch (Exception e) {
            throw new IllegalStateException("Google Sheet CSV 로딩 실패", e);
        }
    }

    private Map<String, Integer> parseHeader(String[] headers) {
        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            indexMap.put(headers[i].trim().toLowerCase(), i);
        }
        return indexMap;
    }
}
