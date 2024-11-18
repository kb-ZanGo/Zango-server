package kb.zango.domain.transaction.service;


import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.entity.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    // 유저로부터 정보(id, pw, accNum) 받아 API 호출
    String apiRequest(String id, String pw, String accNum) throws IOException, InterruptedException;

    List<Transaction> parseApiResponse(Long boardId, String response);

    // 파싱된 데이터를 데이터베이스에 저장
    void saveTransactions(List<Transaction> transactions);

    // 특정 날짜에 해당하는 거래 내역을 가져오는 메서드
    List<TransactionResponseDTO> getTransactionsByDate(Long boardId, String trDay);

}
