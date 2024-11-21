package kb.zango.domain.diary.feedBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFeedBackBoardDTO {

    private String title;
    private String content;

}
