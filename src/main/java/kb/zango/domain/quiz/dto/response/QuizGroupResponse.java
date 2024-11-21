package kb.zango.domain.quiz.dto.response;

import kb.zango.domain.quiz.entity.QuizGroup;
import lombok.Data;

import java.time.LocalDate;

@Data
public class QuizGroupResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDate endDate;

    private String imageUrl;

    private Long count;

    private Long reward;

    public QuizGroupResponse(QuizGroup quizGroup) {
        this.id = quizGroup.getId();
        this.title = quizGroup.getTitle();
        this.description = quizGroup.getDescription();
        this.endDate = quizGroup.getEndDate();
        this.imageUrl = quizGroup.getImageUrl();
        this.count = quizGroup.getCount();
        this.reward = quizGroup.getReward();
    }
}
