package kb.zango.domain.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodefApiRequestDTO {

    private String userId;
    private String userPassword;
    private String userAccountNum;

}
