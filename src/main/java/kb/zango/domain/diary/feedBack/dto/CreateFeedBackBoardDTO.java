package kb.zango.domain.diary.feedBack.dto;

import kb.zango.domain.board.entity.Board;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedBackBoardDTO {
    private List<TransactionResponseDTO> transactions;
    private Board board;
}
