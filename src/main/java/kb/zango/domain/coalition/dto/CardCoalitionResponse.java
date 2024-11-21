package kb.zango.domain.coalition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardCoalitionResponse {
    private double lat;
    private double lon;
    private String address;
    private Long cardId;
    private String type;
    private String name;
    private String content;
}
