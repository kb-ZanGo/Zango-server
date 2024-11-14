package kb.zango.domain.quiz.dto.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserQuizAnswer {
    private Long groupId;
    List<Integer> answer = new ArrayList<>();
}
