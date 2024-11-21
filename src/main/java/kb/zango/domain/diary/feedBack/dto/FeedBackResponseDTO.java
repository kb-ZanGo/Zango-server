package kb.zango.domain.diary.feedBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackResponseDTO {

    private GetBoardDTO board;
    private int incomeMonthSum = 0;
    private int outcomeMonthSum = 0;
    private List<IOCntByDate> ioCnts;

}
