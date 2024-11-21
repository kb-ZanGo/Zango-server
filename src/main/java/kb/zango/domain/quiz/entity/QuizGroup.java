package kb.zango.domain.quiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class QuizGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate endDate;

    private String imageUrl;

    private Long count;

    private Long reward;

    private boolean isUsed;

    public QuizGroup(String title, String description, LocalDate endDate, String imageUrl, Long count, Long reward) {
        this.title = title;
        this.description = description;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.count = count;
        this.reward = reward;
        this.isUsed = false;
    }

    public void use() {
        this.isUsed = true;
    }

    public void addCount() {
        count+=1;
    }
}
