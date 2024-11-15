package kb.zango.domain.quiz.service;

import kb.zango.domain.quiz.entity.Quiz;
import kb.zango.domain.quiz.entity.QuizFactory;
import kb.zango.domain.quiz.entity.QuizGroup;
import kb.zango.domain.quiz.dto.request.QuizRequest;
import kb.zango.domain.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizService {

    private final QuizRepository quizRepository;

    @Transactional
    public List<Quiz> createQuizzes(List<QuizRequest> quiz, QuizGroup quizGroup) {
        List<Quiz> quizzes = new ArrayList<>();
        for (QuizRequest quizRequest : quiz) {
            quizzes.add(QuizFactory.createQuiz(quizGroup, quizRequest.getTitle(), quizRequest.getCorrectAnswer(), quizRequest.getType(),quizRequest.getChoices()));
        }
        return quizRepository.saveAll(quizzes);
    }

    public List<Quiz> findQuizzes(Long quizGroupId) {
        return quizRepository.findByGroupId(quizGroupId);
    }
}
