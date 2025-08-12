package entities.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private String id;
    private UUID userId;
    private String testId;
    private String date;
    private UserChoose answers;
}
