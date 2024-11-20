package kb.zango.domain.diary.feedBack.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IOCntByDate {

    private String date; // 날짜
    private int incomeCnt;  // 수입 개수
    private int outcomeCnt; // 지출 개수
}
