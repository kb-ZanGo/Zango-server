package kb.zango.domain.diary.honeyTip.service;

import static org.junit.jupiter.api.Assertions.*;

import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.mapper.BoardMapper;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardRequest;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardResponse;
import kb.zango.domain.diary.mapper.HoneyTipBoardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class HoneyTipBoardServiceTest {

    @Autowired
    private HoneyTipBoardService honeyTipBoardService;

    @Autowired
    private BoardRepository boardRepository;
//    @Autowired
//    private HoneyTipBoardMapper honeyTipBoardMapper;

    @Autowired
    private BoardMapper boardMapper;

    private HoneyTipBoardRequest request;

    @BeforeEach
    public void setUp() {
        request = new HoneyTipBoardRequest();
        request.setUser_id(1L);
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setSc_id(24L);
        request.setGroupBuyItem("의류공구");
    }

    @Test
    public void testInsertHoneyTipBoard() {
        Long boardId = honeyTipBoardService.insertHoneyTipBoard(request);

        Board board = boardMapper.findBoardById(boardId);
        assertNotNull(board);
        assertEquals("Test Title", board.getTitle());
    }

    @Test
    public void testUpdateHoneyTipBoard() {
        Long boardId = honeyTipBoardService.insertHoneyTipBoard(request);

        Board board = boardRepository.findByBoardIdWithCategory(boardId);
        board.setTitle("Updated Title");
        boardMapper.updateBoard(board);

        // 수정된 제목 확인
        Board updatedBoard = boardMapper.findBoardById(boardId);
        assertEquals("Updated Title", updatedBoard.getTitle());
    }

    @Test
    public void testDeleteHoneyTipBoard() {
        honeyTipBoardService.insertHoneyTipBoard(request);

        honeyTipBoardService.deleteHoneyTipBoard(request.getBoardId());

        Board board = boardMapper.findBoardById(request.getBoardId());
        assertNull(board);
    }


    @Test
    public void testGetHoneyTipBoard() {
        Long boardId = honeyTipBoardService.insertHoneyTipBoard(request);

        HoneyTipBoardResponse response = honeyTipBoardService.getHoneyTipBoard(boardId);
        assertNotNull(response);
        assertEquals("Test Title", response.getTitle());
    }
}