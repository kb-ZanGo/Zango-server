package kb.zango.domain.coalition.entity;

import jakarta.persistence.*;

@Entity
public class CardCoalition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    private Card card;

    private String type; //제휴 브랜드

    private String content; //제휴내용
}
