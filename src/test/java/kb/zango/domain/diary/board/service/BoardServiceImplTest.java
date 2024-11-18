package kb.zango.domain.diary.board.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import kb.zango.domain.board.dto.BoardListResponse;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.service.BoardService;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.board.mapper.BoardMapper;
import kb.zango.domain.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardMapper boardMapper;

    private Board testBoard;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUserId(1L);

        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setScId(1L);

        testBoard = new Board();
        testBoard.setBoardId(1L);
        testBoard.setUser(user);
        testBoard.setSmallCategory(smallCategory);
        testBoard.setTitle("제목 잘들어감?");
        testBoard.setContent("내용 잘들어감?");
        testBoard.setLikeCnt(15);
        testBoard.setRegiDate(LocalDateTime.now());
        testBoard.setBoard_type(0);
    }

    @Test
    void createBoard() {
        boardService.createBoard(testBoard);

        Board savedBoard = boardService.getBoardById(testBoard.getBoardId());

        assertNotNull(savedBoard);
        assertEquals(testBoard.getTitle(), savedBoard.getTitle());
        assertEquals(testBoard.getContent(), savedBoard.getContent());
    }

    @Test
    void getAllBoards() {
        boardMapper.insertBoard(testBoard);

        List<BoardListResponse> boards = boardService.getAllBoards();

        assertNotNull(boards);
        assertTrue(boards.size() > 0);
    }

    @Test
    void updateBoard() {
        boardService.createBoard(testBoard);

        Board savedBoard = boardService.getBoardById(testBoard.getBoardId());
        assertNotNull(savedBoard);

        savedBoard.setTitle("제목수정됨?");
        savedBoard.setContent("내용수정됨?");

        boardService.updateBoard(savedBoard);

        Board updatedBoard = boardService.getBoardById(testBoard.getBoardId());
        assertNotNull(updatedBoard);
        assertEquals("제목수정됨?", updatedBoard.getTitle());
        assertEquals("내용수정됨?", updatedBoard.getContent());
    }

    @Test
    void deleteBoard() {
        boardService.createBoard(testBoard);

        boardService.deleteBoard(testBoard.getBoardId());

        Board deleteBoard = boardService.getBoardById(testBoard.getBoardId());
        assertNull(deleteBoard);
    }
}