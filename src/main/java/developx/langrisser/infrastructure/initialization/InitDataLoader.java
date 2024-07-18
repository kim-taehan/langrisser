package developx.langrisser.infrastructure.initialization;

import developx.langrisser.infrastructure.csv.CsvFileReader;
import developx.langrisser.user.User;
import developx.langrisser.user.UserInfo;
import developx.langrisser.user.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDataLoader {

    public static final String INIT_DATA_CSV = "20240701_small.csv";
    private final CsvFileReader csvFileReader;

    private final UserRepository userRepository;
    @PostConstruct
    @Transactional
    void init() {

        File csvFile = csvFileReader.loadFileByClassLoader(INIT_DATA_CSV);
        if (csvFile.exists()) {
            initData(csvFile);
        }
    }

    private void initData(File csvFile) {

        List<User> list = csvFileReader.loadDataFromCSV(csvFile)
                .stream().map(UserInfo::toEntity)
                .toList();
        userRepository.saveAll(list);
    }


}
