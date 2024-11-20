package kb.zango.domain.quiz.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizGroupResult {

    private List<QuizResult> results = new ArrayList<>();
    private Long correctCount;
    private boolean pass;
    private Long reward;

    public QuizGroupResult(List<QuizResult> results,Long reward) {
        this.results = results;
        this.reward = reward;
        this.correctCount = results.stream().filter(QuizResult::isCorrect).count();
        this.pass = correctCount >= Math.ceil(results.size() / 2.0) && correctCount > 0;
    }

}
