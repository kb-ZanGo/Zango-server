package kb.zango.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentRequestDTO {

    private Long boardId; // 프론트에서 채워서 던져줌
    private Long userId;
    private Long parentCommentId;
    private String content;

}
