package kb.zango.domain.quiz.service;

import kb.zango.domain.quiz.dto.request.UserQuizAnswer;
import kb.zango.domain.quiz.dto.response.QuizGroupResult;
import kb.zango.domain.quiz.dto.response.QuizResponse;
import kb.zango.domain.quiz.dto.response.QuizResult;
import kb.zango.domain.quiz.entity.Choice;
import kb.zango.domain.quiz.entity.Quiz;
import kb.zango.domain.quiz.entity.QuizGroup;
import kb.zango.domain.quiz.dto.request.QuizGroupRequest;
import kb.zango.domain.quiz.dto.request.QuizRequest;
import kb.zango.domain.quiz.visitor.ChoiceVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizFacade {

    private final QuizService quizService;
    private final  QuizGroupService quizGroupService;

    @Transactional
    public void createQuiz(List<QuizRequest> quizRequests, QuizGroupRequest quizGroupRequest) {
        QuizGroup quizGroup = quizGroupService.registerQuizGroup(quizGroupRequest);
        quizService.createQuizzes(quizRequests, quizGroup);
    }

    public List<QuizResponse> findQuizzes(Long quizGroupId) {
        List<Quiz> quizzes = quizService.findQuizzes(quizGroupId);
        List<QuizResponse> result = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            result.add(new QuizResponse(quiz));
        }
        return result;
    }

    public QuizGroupResult solveQuiz(Long quizGroupId, List<Integer> userQuizAnswer) {
        List<Quiz> quizzes = quizService.findQuizzes(quizGroupId);
        List<QuizResult> result = new ArrayList<>();
        for(int i=0;i<quizzes.size();i++) {
            Quiz quiz = quizzes.get(i);
            Integer userAnswer = userQuizAnswer.get(i);
            result.add(new QuizResult(quiz, userAnswer));
        }
        return new QuizGroupResult(result);
    }
}
