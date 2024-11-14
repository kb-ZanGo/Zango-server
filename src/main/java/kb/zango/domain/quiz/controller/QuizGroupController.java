package kb.zango.domain.quiz.controller;

import kb.zango.domain.quiz.dto.request.UserQuizAnswer;
import kb.zango.domain.quiz.dto.response.QuizGroupResponse;
import kb.zango.domain.quiz.dto.response.QuizGroupResult;
import kb.zango.domain.quiz.service.QuizFacade;
import kb.zango.domain.quiz.service.QuizGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizGroupController {

    private final QuizGroupService quizGroupService;
    private final QuizFacade quizFacade;

    @GetMapping("/api/dailyQuiz")
    public List<QuizGroupResponse> getDailyQuizGroups() {
        return quizGroupService.getDailyQuizGroups();
    }

    @PostMapping("/api/dailyQuiz/result")
    public QuizGroupResult getResult(@RequestBody UserQuizAnswer userQuizAnswer) {
        return quizFacade.solveQuiz(userQuizAnswer.getGroupId(), userQuizAnswer.getAnswer());
    }

}
