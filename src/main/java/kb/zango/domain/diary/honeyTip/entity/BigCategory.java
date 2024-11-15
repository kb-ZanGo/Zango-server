package kb.zango.domain.diary.honeyTip.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="big_category")
public class BigCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bcId;

    @Column(nullable = false)
    public String name;

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.REMOVE)
    private List<SmallCategory> smallCategories;
}
