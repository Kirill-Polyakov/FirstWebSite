package entities.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private String id;
    private String testName;
    private String categoryName;
    private UUID createdBy;
    private List<Question> questions;
}
