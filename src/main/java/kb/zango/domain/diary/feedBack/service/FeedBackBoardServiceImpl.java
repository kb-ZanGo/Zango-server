package kb.zango.domain.diary.feedBack.service;


import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.diary.feedBack.dto.FeedBackResponseDTO;
import kb.zango.domain.diary.feedBack.dto.GetBoardDTO;
import kb.zango.domain.diary.feedBack.dto.IOCntByDate;
import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import kb.zango.domain.diary.feedBack.repository.FeedBackRepository;
import kb.zango.domain.diary.honeyTip.repository.SmallCategoryRepository;
import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.entity.Transaction;
import kb.zango.domain.transaction.service.TransactionService;
import kb.zango.domain.users.entity.User;
import kb.zango.domain.users.repository.UserRepository;
import kb.zango.global.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class FeedBackBoardServiceImpl implements FeedBackBoardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private SmallCategoryRepository smallCategoryRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private FeedBackRepository feedBackRepository;


    // 거래내역 가져올수있는지 확인
    @Override
    public List<TransactionResponseDTO> checkConnectedId(CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException {
        String response = transactionService.apiRequest(codefApiRequestDTO);
        return transactionService.parseApiResponse(response);
    }

    // 피드백 게시물 저장
    @Override
    public void createFeedBackBoard(Board board, List<TransactionResponseDTO> transactions) {
        // 1. board 만들고(title, content)
        FeedBackBoard feedBackBoard = new FeedBackBoard();
        feedBackBoard.setBoard(board);

        // 게시물 저장
        feedBackRepository.save(feedBackBoard);

        // 트랜잭션 저장
        // transactionResponseDTO -> Transaction
        List<Transaction> transactionList = new ArrayList<>();
        for (TransactionResponseDTO transactionDTO : transactions) {
            Transaction transaction = new Transaction();

            transaction.setFeedBackBoard(feedBackBoard);
            transaction.setTrType(transactionDTO.getTrType());
            transaction.setTrName(transactionDTO.getTrName());
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setTrDay(transactionDTO.getTrDay());
            transaction.setTrTime(transactionDTO.getTrTime());
            transactionList.add(transaction);
        }

        transactionService.saveTransactions(transactionList);
    }


    @Override
    public void deleteFeedBackBoard(Long boardId) {
        Long feedBackId = feedBackRepository.findFeedBackIdByBoardId(boardId).orElseThrow(() -> new ResourceNotFoundException("FeedBackBoardServiceImpl_deleteFeedBackBoard: 해당하는 게시물의 피드백 게시물 아이디를 찾을 수 없습니다: " + boardId));
        feedBackRepository.deleteById(feedBackId);
    }

    /**
     * 해당하는 게시물 모두 가져오고 board & transactions 담아서 return
     * @param boardId
     * @return
     */
    @Override
    public FeedBackResponseDTO getTransactions(Long boardId) {
        // 1. boardId로 해당 게시물이 존재하는지 확인
        Long feedBackId = feedBackRepository.findFeedBackIdByBoardId(boardId).orElseThrow(() -> new ResourceNotFoundException("FeedBackBoardServiceImpl_getTransactions: 해당하는 feedBack 게시물 아이디를 가져오지 못했습니다: "+ boardId));
        Board board = boardRepository.findByBoardId(boardId);

        // 응답에 넣을 board 정보
        Board foundboard = boardRepository.findByBoardId(boardId);

        GetBoardDTO newBoard = new GetBoardDTO();
        newBoard.setBoardId(foundboard.getBoardId());
        newBoard.setUsername(board.getUser().getUsername());
        newBoard.setTitle(foundboard.getTitle());
        newBoard.setContent(foundboard.getContent());
        newBoard.setRegiDate(foundboard.getRegiDate());
        newBoard.setLike_cnt(foundboard.getLikeCnt());
        newBoard.setComment_cnt(foundboard.getCommentCnt());
        newBoard.setView_cnt(foundboard.getView_cnt());

        // 응답에 넣을 transactions 정보
        List<TransactionResponseDTO> transactions = transactionService.getTransactions(feedBackId);

        // Map을 사용하여 날짜별로 수입 및 지출을 집계할 수 있는 구조 생성
        Map<String, IOCntByDate> countMap = new HashMap<>();

        for (TransactionResponseDTO transaction : transactions) {
            String date = transaction.getTrDay(); // 트랜잭션의 날짜 가져오기

            // 날짜에 해당하는 집계 객체가 없으면 새로 생성
            IOCntByDate ioCnt = countMap.getOrDefault(date, new IOCntByDate(date, 0, 0));

            // 트랜잭션 타입에 따라 수입/지출 개수 증가
            if (transaction.getTrType() == 1) { // 수입
                ioCnt.setIncomeCnt(ioCnt.getIncomeCnt() + 1);
            } else if (transaction.getTrType() == 0) { // 지출
                ioCnt.setOutcomeCnt(ioCnt.getOutcomeCnt() + 1);
            }

            // Map에 갱신된 값 저장
            countMap.put(date, ioCnt);
        }

        // 결과를 List로 변환
        List<IOCntByDate> ioCntByDateList = new ArrayList<>(countMap.values());

        FeedBackResponseDTO boardWithTransactions = new FeedBackResponseDTO();
        boardWithTransactions.setBoard(newBoard);
        boardWithTransactions.setIoCnts(ioCntByDateList);

        return boardWithTransactions;
    }

    // 게시물 Id와 날짜로 조회
    @Override
    public List<TransactionResponseDTO> getTransactionsByDate(Long feedBackId, String trDay) {
        feedBackRepository.findById(feedBackId).orElseThrow(() -> new kb.zango.global.exception.ResourceNotFoundException("해당하는 게시물을 찾을 수 없습니다: " + feedBackId));

        return transactionService.getTransactionsByDate(feedBackId, trDay);
    }
}
