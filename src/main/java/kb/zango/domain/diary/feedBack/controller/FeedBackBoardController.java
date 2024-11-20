package kb.zango.domain.diary.feedBack.controller;


import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.diary.feedBack.dto.*;
import kb.zango.domain.diary.feedBack.repository.FeedBackRepository;
import kb.zango.domain.diary.feedBack.service.FeedBackBoardServiceImpl;
import kb.zango.domain.transaction.dto.CodefApiRequestDTO;
import kb.zango.domain.transaction.dto.TransactionResponseDTO;
import kb.zango.domain.transaction.service.TransactionServiceImpl;
import kb.zango.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/feedback")
public class FeedBackBoardController {

    private final FeedBackBoardServiceImpl feedBackBoardService;
    private final BoardRepository boardRepository;
    private final FeedBackRepository feedBackRepository;
    private final FeedBackBoardServiceImpl feedBackBoardServiceImpl;

    @PostMapping("/login")
    @Transactional(rollbackFor = {IOException.class, InterruptedException.class})
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionByCallCodefApi(@RequestBody CodefApiRequestDTO codefApiRequestDTO) throws IOException, InterruptedException {
        List<TransactionResponseDTO> transactionResponseDTOs = feedBackBoardService.checkConnectedId(codefApiRequestDTO);
        return ResponseEntity.ok(transactionResponseDTOs);
    }

    // 생성
    @PostMapping()
    @Transactional(rollbackFor = {IOException.class, InterruptedException.class})
    public ResponseEntity<Void> createFeedBackBoard(@RequestBody CreateFeedBackBoardDTO createFeedBackBoardDTO) throws IOException, InterruptedException {
        feedBackBoardService.createFeedBackBoard(createFeedBackBoardDTO.getBoard(), createFeedBackBoardDTO.getTransactions());
        return ResponseEntity.ok().build();
    }

    // 수정
    @PutMapping
    public ResponseEntity<Void> updateFeedBackBoard(@RequestBody Board board){
        Board findBoard = boardRepository.findByBoardId(board.getBoardId());
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());

        boardRepository.save(findBoard);

        return ResponseEntity.ok().build();
    }


    // 삭제
    @DeleteMapping("/{boardId}")
    @Transactional
    public ResponseEntity<Void> deleteFeedBackBoard(@PathVariable Long boardId){
        feedBackBoardService.deleteFeedBackBoard(boardId);
        return ResponseEntity.ok().build();
    }


    //  조회
    @GetMapping("/{boardId}")
    public ResponseEntity<FeedBackResponseDTO> getTransactionsByBoardId(@PathVariable Long boardId) {
        FeedBackResponseDTO transactions = feedBackBoardServiceImpl.getTransactions(boardId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{boardId}/{date}")
    public ResponseEntity<DateSumResponseDTO> getTransactionsByBoardIdAndDate(@PathVariable Long boardId, @PathVariable String date) {
        // boardId로 feedBackId 찾기
        Long feedBackId = feedBackRepository.findFeedBackIdByBoardId(boardId).orElseThrow(() -> new ResourceNotFoundException("FeedBackBoardController_getTransactionsByBoardIdAndDate: 해당하는 게시물의 피드백 아이디를 찾을 수 없음: " + boardId));
        DateSumResponseDTO transactionsByDate = feedBackBoardServiceImpl.getTransactionsByDate(feedBackId, date);
        return ResponseEntity.ok(transactionsByDate);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HomeListFeedBackDTO>> getBoards() {
        List<HomeListFeedBackDTO> feedBackList = feedBackBoardServiceImpl.getFeedBackList();
        return ResponseEntity.ok(feedBackList);
    }

    @PostMapping("/{boardId}/like")
    public ResponseEntity<Void> likeFeedBackBoardByBoardId(@PathVariable Long boardId) {
        feedBackBoardServiceImpl.likeFeedBack(boardId);
        return ResponseEntity.ok().build();
    }
}
