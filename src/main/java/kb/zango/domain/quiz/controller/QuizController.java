package kb.zango.domain.quiz.controller;

import kb.zango.common.Result;
import kb.zango.domain.quiz.dto.response.QuizResponse;
import kb.zango.domain.quiz.entity.Quiz;
import kb.zango.domain.quiz.dto.request.QuizCreateRequest;
import kb.zango.domain.quiz.service.QuizFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizFacade quizFacade;

    @PostMapping
    public void createQuiz(@RequestBody(required = false) QuizCreateRequest quizRequest) {
        quizFacade.createQuiz(quizRequest.getQuizzes(),quizRequest.getGroup());
    }

    @GetMapping("/quizGroup/{id}")
    public Result<List<QuizResponse>> getQuizGroup(@PathVariable Long id) {
        return new Result<>(quizFacade.findQuizzes(id));
    }

}
