package kb.zango.domain.diary.feedBack.service;

import kb.zango.domain.board.entity.Board;
import kb.zango.domain.diary.feedBack.dto.DateSumResponseDTO;
import kb.zango.domain.diary.feedBack.dto.FeedBackResponseDTO;
import kb.zango.domain.diary.feedBack.dto.GetBoardDTO;
import kb.zango.domain.diary.feedBack.dto.HomeListFeedBackDTO;
import kb.zango.domain.diary.feedBack.entity.FeedBackBoard;
import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;

import java.io.IOException;
import java.util.List;

public interface FeedBackBoardService {

    // connectedId 발급 가능한지 확인
    List<TransactionResponseDTO> checkConnectedId(CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException;

    // 피드백 게시물 작성 & 거래내역 저장
    void createFeedBackBoard(Board board, List<TransactionResponseDTO> transactions);

    void deleteFeedBackBoard(Long boardId);

    // 게시물 모든 거래내역 조회
    FeedBackResponseDTO getTransactions(Long boardId);

    // 게시물 Id와 날짜로 조회
    DateSumResponseDTO getTransactionsByDate(Long boardId, String date);

    // 게시물 목록 조회(홈)
    List<HomeListFeedBackDTO> getFeedBackList();

    void likeFeedBack(Long boardId);
}
