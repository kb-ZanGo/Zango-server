package kb.zango.domain.diary.feedBack.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeListFeedBackDTO {

    private Long feedBackId;
    private String username;
    private String title;
    private String content;
    private String regiDate;
    private int likeCnt;

}
