package kb.zango.domain.quiz.dto.request;

import kb.zango.domain.quiz.entity.Choice;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizRequest {

    private String title;

    private Integer correctAnswer;

    private String type;

    private List<Choice> choices = new ArrayList<>();
}
