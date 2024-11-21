package kb.zango.domain.quiz.service;

import kb.zango.domain.quiz.entity.QuizGroup;
import kb.zango.domain.quiz.dto.request.QuizGroupRequest;
import kb.zango.domain.quiz.dto.response.QuizGroupResponse;
import kb.zango.domain.quiz.repository.QuizGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizGroupService {

    private final QuizGroupRepository quizGroupRepository;
    private List<QuizGroup> dailyQuizGroups = new ArrayList<>();

    @Transactional
    public QuizGroup registerQuizGroup(QuizGroupRequest quizGroupRequest) {
        return quizGroupRepository.save(quizGroupRequest.toEntity());
    }

//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void fetchDailyQuizGroups() {
        List<QuizGroup> all = quizGroupRepository.findAll();
        Collections.shuffle(all);
        dailyQuizGroups = all.stream()
                .limit(3)
                .toList();
        dailyQuizGroups.forEach(QuizGroup::use);
    }

    @Cacheable("dailyQuizGroups")
    public List<QuizGroupResponse> getDailyQuizGroups() {
        return dailyQuizGroups.stream()
                .map(QuizGroupResponse::new)
                .toList();
    }

}
