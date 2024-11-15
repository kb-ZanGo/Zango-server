package kb.zango.domain.quiz.dto.response;

import kb.zango.domain.quiz.entity.Quiz;
import kb.zango.domain.quiz.visitor.ChoiceVisitor;
import lombok.Data;

@Data
public class QuizResult {

    private String quizTitle;
    private String answer;
    private boolean correct;

    public QuizResult(Quiz quiz,Integer userAnswer) {
        this.quizTitle = quiz.getTitle();
        Integer correctAnswer = quiz.getCorrectAnswer();
        this.answer = quiz.getType().equals("OX")
                ? (correctAnswer == 0 ? "O" : "X")
                : quiz.accept(new ChoiceVisitor()).get(correctAnswer-1).getText();
        this.correct = correctAnswer.equals(userAnswer);
    }

}
