package kb.zango.domain.diary.feedBack.dto;

import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateSumResponseDTO {

    private int incomeDateSum = 0;
    private int outcomeDateSum = 0;
    List<TransactionResponseDTO> transactions;

}
