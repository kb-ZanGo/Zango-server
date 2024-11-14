package kb.zango.domain.quiz.entity;

import java.util.List;

public class QuizFactory {

    public static Quiz createQuiz(QuizGroup quizGroup, String title, Integer correctAnswer, String type, List<Choice> choices) {
        if ("OX".equalsIgnoreCase(type)) {
            return new OXQuiz(quizGroup,title,correctAnswer);
        } else if ("MCQ".equalsIgnoreCase(type)) {
            return new MultipleChoiceQuiz(quizGroup,title,correctAnswer,choices);
        } else {
            throw new IllegalArgumentException("Unknown quiz type: " + type);
        }
    }

}
