package kb.zango.domain.quiz.dto.response;

import kb.zango.domain.quiz.entity.Choice;
import kb.zango.domain.quiz.entity.Quiz;
import kb.zango.domain.quiz.visitor.ChoiceVisitor;
import lombok.Data;

import java.util.List;

@Data
public class QuizResponse {

    private Long id;

    private String title;

    private Integer correctAnswer;

    private List<Choice> choices;

    private String type;

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.correctAnswer = quiz.getCorrectAnswer();
        this.type = quiz.getType();
        this.choices = quiz.accept(new ChoiceVisitor());
    }
}
