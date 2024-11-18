package kb.zango.domain.diary.honeyTip.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "small_category")
public class SmallCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bc_id", nullable = false)
    private BigCategory bigCategory;

    private String name;

}
