package kb.zango.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private Long commentId;
    private Long boardId;
    private String userName;
    private Long parentCommentId;
    private String content;
    private LocalDateTime regiDate;
    private int likeCnt;

}
