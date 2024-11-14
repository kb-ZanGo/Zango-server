package kb.zango.domain.quiz.visitor;

import kb.zango.domain.quiz.entity.Choice;
import kb.zango.domain.quiz.entity.MultipleChoiceQuiz;
import kb.zango.domain.quiz.entity.OXQuiz;

import java.util.List;

public interface Visitor {

    List<Choice> visit(OXQuiz oxQuiz);
    List<Choice> visit(MultipleChoiceQuiz multipleChoiceQuiz);
}
