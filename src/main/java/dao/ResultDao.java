package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.results.Result;
import entities.results.ResultList;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;

@AllArgsConstructor
public class ResultDao {
    private final ObjectMapper objectMapper;
    private final File file;

    @SneakyThrows
    public void saveResults(Result result) {
        ResultList resultsList = getAllResults();
        resultsList.getResults().add(result);
        objectMapper.writeValue(file, resultsList);
    }

    @SneakyThrows
    public ResultList getAllResults() {
        if (file.exists() && file.length() != 0) {
            return objectMapper.readValue(file, ResultList.class);
        } else {
            return new ResultList(new ArrayList<>());
        }
    }
}
