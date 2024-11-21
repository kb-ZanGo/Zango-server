package kb.zango.domain.quiz.visitor;

import kb.zango.domain.quiz.entity.Choice;
import kb.zango.domain.quiz.entity.MultipleChoiceQuiz;
import kb.zango.domain.quiz.entity.OXQuiz;

import java.util.List;

public class ChoiceVisitor implements Visitor{
    @Override
    public List<Choice> visit(OXQuiz oxQuiz) {
        return List.of();
    }

    @Override
    public List<Choice> visit(MultipleChoiceQuiz multipleChoiceQuiz) {
        return multipleChoiceQuiz.getChoices() ;
    }
}
