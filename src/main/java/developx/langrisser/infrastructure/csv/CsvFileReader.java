package developx.langrisser.infrastructure.csv;

import com.opencsv.CSVReader;
import developx.langrisser.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CsvFileReader {

    // Rank(P),Point,Rank(C),Cristal,
    // Name,Server
    // 이전아이디,비고,여단,SRP,SRC
    public List<UserInfo> loadDataFromCSV(File csvFile) {

        ArrayList<UserInfo> result = new ArrayList<>();
        try ( FileReader reader = new FileReader(csvFile);
              CSVReader csvReader = new CSVReader(reader);
        ) {

            // 헤더을 읽어서 필요한 키값과 인덱스 데이터를 별도의 데이터로 저장한다.
            HashMap<String, Integer> headers = createHeaderData(csvReader.readNext());
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                // 모든 줄을 읽어서 컬럼 기준 첫번째는 '도'에 해당하는 이름
                UserInfo userInfo = UserInfo.from(headers, nextRecord);
                result.add(userInfo);
                log.info("userInfo={}", userInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private HashMap<String, Integer> createHeaderData(String[] headerLine) throws IOException {
        HashMap<String, Integer> headers = new HashMap<>();
        for (int i = 0; i < headerLine.length; i++) {
            headers.put(headerLine[i].toLowerCase(), i);
        }
        return headers;
    }

    public File loadFileByClassLoader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        return Objects.isNull(resource) ? null : new File(resource.getFile());
    }


    public static void main(String[] args) {
        CsvFileReader csvFileReader = new CsvFileReader();
        File file = csvFileReader.loadFileByClassLoader("20240701_small.csv");
        csvFileReader.loadDataFromCSV(file);

    }
}
