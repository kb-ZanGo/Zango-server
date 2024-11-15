package kb.zango.domain.quiz.repository;

import kb.zango.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q from Quiz q join fetch q.group where q.group.id = :groupId")
    List<Quiz> findByGroupId(Long groupId);
}
