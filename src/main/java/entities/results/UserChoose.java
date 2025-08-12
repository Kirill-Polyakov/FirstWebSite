package entities.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChoose {
    // Map <key = questionId, Map<key = selectedAnswerId, value = isCorrect>>
    private Map<String, Map<String, Boolean>> isCorrect;
}
