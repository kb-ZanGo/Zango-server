package kb.zango.domain.transaction;

import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.entity.Transaction;
import kb.zango.domain.transaction.repository.TransactionRepository;
import kb.zango.domain.transaction.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    public void testFetchAndParseTransactions() throws Exception {
        // 테스트 데이터
        String testId = "HCI6870";
        String testPw = "chan026509";
        String testAccNum = "94290200113525";
        Long testBoardId = 5l; // 존재하는 게시물 ID로 설정

        // 1. API 요청 수행 (가짜 데이터가 반환된다고 가정)
        String jsonResponse = transactionService.apiRequest(testId, testPw, testAccNum);

        // 2. 응답 데이터 파싱
        List<Transaction> transactions = transactionService.parseApiResponse(testBoardId, jsonResponse);

        // 3. DB에 저장
        transactionService.saveTransactions(transactions);

        // 4. 특정 조건으로 데이터 가져오기 (예시: 특정 날짜)
        String testDate = "20241117"; // 테스트 날짜 예시
        List<TransactionResponseDTO> result = transactionService.getTransactionsByDate(testBoardId, testDate);

        // 5. 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0); // 데이터가 존재하는지 확인
        result.forEach(dto -> System.out.println(dto));
    }


//    @Test
//    void register() throws Exception {
//        // 테스트에 사용할 더미 데이터
//        String testUserId = "userid";
//        String testUserPassword = "userpassword";
//        String testAccountNum = "accountnum";
//
//        // TransactionService의 register 메서드를 호출
//        String result = transactionService.register(testUserId, testUserPassword, testAccountNum);
//
//        // 결과가 null이 아닌지 검증
//        assertNotNull(result, "The result should not be null");
//    }
}