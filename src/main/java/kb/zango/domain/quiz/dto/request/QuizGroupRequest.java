package kb.zango.domain.quiz.dto.request;

import kb.zango.domain.quiz.entity.QuizGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QuizGroupRequest {

    private String title;

    private String description;

    private LocalDate endDate;

    private String imageUrl;

    private Long reward;

    public QuizGroup toEntity() {
        return new QuizGroup(title,description, endDate, imageUrl, 0L, reward);
    }
}
