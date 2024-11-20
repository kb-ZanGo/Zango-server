package kb.zango.domain.diary.honeyTip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeListHoneyBoardDTO {

    private Long HoneyBoardId;
    private String title;
    private String username;
    private int viewCnt;
    private int likeCnt;

}
