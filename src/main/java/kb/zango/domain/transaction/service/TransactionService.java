package kb.zango.domain.transaction.service;


import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.entity.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    // 유저로부터 정보(id, pw, accNum) 받아 API 호출
    String apiRequest(CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException;

    List<TransactionResponseDTO> parseApiResponse(String response);

    // 파싱된 데이터를 데이터베이스에 저장
    void saveTransactions(List<Transaction> transactions);

    // 특정 게시물에 해당하는 거래내역 가져오는 메서드
    List<TransactionResponseDTO> getTransactions(Long feedBackBoardId);

    // 특정 날짜에 해당하는 거래 내역을 가져오는 메서드
    List<TransactionResponseDTO> getTransactionsByDate(Long feedBackBoardId, String trDay);

}
