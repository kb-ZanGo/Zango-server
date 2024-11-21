package kb.zango.domain.diary.honeyTip.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
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

//    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    private List<SmallCategory> smallCategories;

}
