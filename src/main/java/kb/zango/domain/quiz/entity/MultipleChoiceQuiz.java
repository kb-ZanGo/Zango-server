package kb.zango.domain.quiz.entity;

import jakarta.persistence.*;
import kb.zango.domain.quiz.visitor.Visitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("MCQ")
@Getter
@NoArgsConstructor
public class MultipleChoiceQuiz extends Quiz {

    @ElementCollection
    @CollectionTable(name = "quiz_choice", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<Choice> choices;

    public MultipleChoiceQuiz(QuizGroup quizGroup, String title, Integer correctAnswer,List<Choice> choices) {
        super(quizGroup, title, correctAnswer);
        this.choices = choices;
    }

    @Override
    public String getType() {
        return "MCQ";
    }

    @Override
    public List<Choice> accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
