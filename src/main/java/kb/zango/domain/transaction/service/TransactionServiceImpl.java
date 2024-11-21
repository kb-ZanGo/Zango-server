package kb.zango.domain.transaction.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import kb.zango.domain.diary.feedBack.repository.FeedBackRepository;
import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
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
    private final FeedBackRepository feedBackRepository;


    /**
     *  유저 정보 입력받아 api 호출하는 메서드
     *
     * @return JSON 데이터
     */
    @Override
    public String apiRequest(CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException {
        return codefTransactionService.register(codefApiRequestDTO);
    }


    /**
     * JSON 데이터 파싱해 리스트로 추출
     *
     * @param response codef api 호출 후 받은 json 데이터
     * @return 거래내역 리스트(파싱 처리)
     */
    @Override
    public List<TransactionResponseDTO> parseApiResponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        List<TransactionResponseDTO> transactions = new ArrayList<>();

        try{
            JsonNode rootNode = mapper.readTree(response);
            JsonNode dataNode = rootNode.path("data");
            JsonNode historyListNode = dataNode.path("resTrHistoryList");

            if(historyListNode.isArray()){
                for(JsonNode historyNode : historyListNode){
                    TransactionResponseDTO transaction = new TransactionResponseDTO();

                    // 매핑
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
    private static void setTrType(JsonNode historyNode, TransactionResponseDTO transaction) {
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


    @Override
    public List<TransactionResponseDTO> getTransactions(Long feedBackId) {

        FeedBackBoard feedBackBoard = feedBackRepository.findByFeedBackId(feedBackId)
                .orElseThrow(() -> new kb.zango.global.exception.ResourceNotFoundException(
                        "TransactionServiceImpl_getTransactions: 해당하는 게시물 id를 찾을 수 없습니다. " + feedBackId
                ));

        // 특정 게시물에 연결된 거래내역 조회
        List<Transaction> transactions = transactionRepository.findTransactionsByFeedBackId(feedBackId);

        return transactions.stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getTrType(),
                        transaction.getTrName(),
                        transaction.getAmount(),
                        transaction.getTrDay(),
                        transaction.getTrTime()
                ))
                .collect(Collectors.toList());
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
