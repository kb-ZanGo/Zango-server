package kb.zango.domain.transaction.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.entity.Transaction;
import kb.zango.domain.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CodefTransactionService codefTransactionService;
    private final BoardRepository boardRepository;


    /**
     *  유저 정보 입력받아 api 호출하는 메서드
     *
     * @param id KB user id
     * @param pw KB user password
     * @param accNum KB user Account Number
     * @return
     */
    @Override
    public String apiRequest(String id, String pw, String accNum) throws IOException, InterruptedException {
        return codefTransactionService.register(id, pw, accNum);
    }


    /**
     * JSON 데이터 파싱해 리스트로 추출
     *
     * @param response codef api 호출 후 받은 json 데이터
     * @return
     */
    @Override
    public List<Transaction> parseApiResponse(Long boardId, String response) {
        ObjectMapper mapper = new ObjectMapper();
        List<Transaction> transactions = new ArrayList<>();
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new kb.zango.global.exception.ResourceNotFoundException("TransactionServcieImpl_parseApiRespnse 해당하는 게시물 id를 찾을 수 없습니다. "+ boardId));

        try{
            JsonNode rootNode = mapper.readTree(response);
            JsonNode dataNode = rootNode.path("data");
            JsonNode historyListNode = dataNode.path("resTrHistoryList");

            if(historyListNode.isArray()){
                for(JsonNode historyNode : historyListNode){
                    Transaction transaction = new Transaction();

                    // 매핑
                    transaction.setBoard(board);
                    setTrType(historyNode, transaction); // trType, trAmount 설정
                    transaction.setTrName(historyNode.path("resAccountDesc3").asText("기타"));
                    transaction.setTrDay(historyNode.path("resAccountTrDate").asText("날짜알수없음"));
                    transaction.setTrTime(historyNode.path("resAccountTrTime").asText("시간알수없음"));

                    transactions.add(transaction);
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return transactions;
    }

    // trType, trAmount 정하는 함수
    private static void setTrType(JsonNode historyNode, Transaction transaction) {
        long accountOut = historyNode.path("resAccountOut").asLong(0);
        long accountIn = historyNode.path("resAccountIn").asLong(0);

        if(accountOut > 0 ){
            transaction.setTrType(0);
            transaction.setAmount(accountOut);
        }else{
            transaction.setTrType(1);
            transaction.setAmount(accountIn);
        }
    }


    /**
     *  db 저장 메서드
     * @param transactions
     */
    @Override
    public void saveTransactions(List<Transaction> transactions) {
        transactionRepository.saveAll(transactions);
    }


    /**
     * 게시물 id에 해당하는 거래내역의 특정 날짜의 거래내역 리스트를 가져옴
     *
     * @param boardId 해당하는 게시물 id
     * @param trDay 가져오려는 날짜
     * @return
     */
    @Override
    public List<TransactionResponseDTO> getTransactionsByDate(Long boardId, String trDay) {

        // 게시물의 거래내역 중 해당하는 날짜만 List로 가져오기
        List<Transaction> transactions = transactionRepository.findByBoard_BoardIdAndTrDay(boardId, trDay);

        return transactions.stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getTrType(),
                        transaction.getTrName(),
                        transaction.getAmount(),
                        transaction.getTrDay(),
                        transaction.getTrTime()
                ))
                .collect(Collectors.toList()); // collect() 메서드로 리스트 수집
    }
}
