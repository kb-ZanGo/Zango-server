package kb.zango.domain.store.dto;

import kb.zango.domain.store.entity.Store;
import lombok.Data;

import java.time.LocalTime;

@Data
public class StoreResponse {
    private String name;
    private String tel;
    private String addr;
    private String road;
    private Double x;
    private Double y;
    private LocalTime startTime;
    private LocalTime endTime;

    public StoreResponse(Store store) {
        this.name = store.getName();
        this.tel = store.getTel();
        this.addr = store.getAddr() + " " + store.getAddr2();
        this.road = store.getRoad() + " " + store.getRoad2();
        this.x = store.getX();
        this.y = store.getY();
        this.startTime = store.getStartTime();
        this.endTime = store.getEndTime();
    }
}
