package kb.zango.domain.quiz.repository;

import kb.zango.domain.quiz.entity.QuizGroup;
import org.springframework.data.repository.CrudRepository;

public interface QuizGroupRedisRepository extends CrudRepository<QuizGroup, String> {
}