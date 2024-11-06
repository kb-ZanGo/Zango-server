package kb.zango.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalTime;

@Entity
@Getter
public class Store {

    @Id
    private Long id;

    private String name;
    private String tel;
    private String addr;
    private String addr2;
    private String road;
    private String road2;
    private Double x;
    private Double y;
    private LocalTime startTime;
    private LocalTime endTime;

}
