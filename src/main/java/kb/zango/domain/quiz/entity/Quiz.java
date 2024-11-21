package kb.zango.domain.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kb.zango.domain.quiz.visitor.Visitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "quiz_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Quiz{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private QuizGroup group;

    private String title;

    protected Integer correctAnswer;

    public Quiz(QuizGroup group, String title, Integer correctAnswer) {
        this.group = group;
        this.title = title;
        this.correctAnswer = correctAnswer;
    }

    public boolean solve(Integer answer) {
        return answer == correctAnswer;
    }

    public abstract List<Choice> accept(Visitor visitor);

    public abstract String getType();
}
