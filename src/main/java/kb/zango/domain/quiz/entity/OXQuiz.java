package kb.zango.domain.quiz.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import kb.zango.domain.quiz.visitor.Visitor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("OX")
@NoArgsConstructor
public class OXQuiz extends Quiz {

    public OXQuiz(QuizGroup quizGroup,String title, Integer correctAnswer) {
        super(quizGroup, title, correctAnswer);
    }

    @Override
    public List<Choice> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getType() {
        return "OX";
    }

}
