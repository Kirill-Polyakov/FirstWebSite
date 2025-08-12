package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.tests.Test;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TestDao {
    private final ObjectMapper objectMapper;
    private final String TESTS_DIRECTORY_PATH = "C:/Users/Kirill/IdeaProjects/FirstWebSite/src/main/resources/data/tests/";

    public List<Test> findAllTests() {
        File directoryPath = new File(TESTS_DIRECTORY_PATH);
        if (!directoryPath.exists()) {
            return new ArrayList<>();
        }
        File[] files = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".json"));
        if (files == null || files.length == 0) {
            return new ArrayList<>();
        }
        List<Test> tests = new ArrayList<>(files.length);
        for (File file : files) {
            try {
                tests.add(objectMapper.readValue(file, Test.class));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tests;
    }

    public void saveTest(Test test) {
        Path path = Path.of(TESTS_DIRECTORY_PATH + "test" + Integer.parseInt(test.getId()) + ".json");
        try {
            objectMapper.writeValue(new File(path.toUri()), test);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTest(Test test) {
        Path path = Path.of(TESTS_DIRECTORY_PATH + "test" + Integer.parseInt(test.getId()) + ".json");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
