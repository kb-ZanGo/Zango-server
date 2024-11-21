package kb.zango.domain.diary.feedBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardDTO {

    private Long boardId;
    private String username;
    private String title;
    private String content;
    private LocalDateTime regiDate;
    private int like_cnt;
    private int comment_cnt;
    private int view_cnt;
}
