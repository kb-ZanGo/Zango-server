package kb.zango.domain.quiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kb.zango.domain.quiz.entity.QuizGroup;
import kb.zango.domain.quiz.dto.request.QuizGroupRequest;
import kb.zango.domain.quiz.dto.response.QuizGroupResponse;
import kb.zango.domain.quiz.repository.QuizGroupRedisRepository;
import kb.zango.domain.quiz.repository.QuizGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final RedisTemplate<String, Object> redisTemplate;
    private final QuizGroupRedisRepository quizGroupRedisRepository;
    private final ObjectMapper objectMapper;

//    private List<QuizGroup> dailyQuizGroups = new ArrayList<>();

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
        List<QuizGroup> dailyQuizGroups = all.stream()
                .limit(3)
                .toList();
        dailyQuizGroups.forEach(QuizGroup::use);
        try {
            String quizGroupJson = objectMapper.writeValueAsString(dailyQuizGroups);
            redisTemplate.opsForValue().set("quiz", quizGroupJson);
        } catch (JsonProcessingException e) {
            System.err.println("Redis에 퀴즈 그룹 저장 중 오류 발생: " + e.getMessage());
        }
    }

    @Cacheable("quiz")
    public List<QuizGroupResponse> getDailyQuizGroups() {
        List<QuizGroupResponse> result = new ArrayList<>();

        String quizGroupJson = (String) redisTemplate.opsForValue().get("quiz");
        if (quizGroupJson == null) {
            return result;
        }

        try {
            QuizGroup[] quizGroups = objectMapper.readValue(quizGroupJson, QuizGroup[].class);
            for (QuizGroup quizGroup : quizGroups) {
                result.add(new QuizGroupResponse(quizGroup));
            }
        } catch (JsonProcessingException e) {
            System.err.println("Redis에서 퀴즈 그룹 조회 중 오류 발생: " + e.getMessage());
        }


        return result;
    }

}
