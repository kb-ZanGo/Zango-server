package kb.zango.domain.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private int trType;
    private String trName;
    private long amount;
    private String trDay;
    private String trTime;

}
