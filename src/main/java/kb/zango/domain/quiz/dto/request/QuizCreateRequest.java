package kb.zango.domain.quiz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuizCreateRequest {
    private QuizGroupRequest group;
    private List<QuizRequest> quizzes;
}
