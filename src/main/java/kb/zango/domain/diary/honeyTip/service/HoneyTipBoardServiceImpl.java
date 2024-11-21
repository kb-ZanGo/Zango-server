package kb.zango.domain.diary.honeyTip.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kb.zango.domain.board.entity.Board;
import kb.zango.domain.board.mapper.BoardMapper;
import kb.zango.domain.board.repository.BoardRepository;
import kb.zango.domain.diary.honeyTip.dto.HomeListHoneyBoardDTO;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardRequest;
import kb.zango.domain.diary.honeyTip.dto.HoneyTipBoardResponse;
import kb.zango.domain.diary.honeyTip.entity.HoneyTipBoard;
import kb.zango.domain.diary.honeyTip.entity.GroupBuyBoard;
import kb.zango.domain.diary.honeyTip.entity.SmallCategory;
import kb.zango.domain.diary.honeyTip.repository.GroupBuyBoardRepository;
import kb.zango.domain.diary.mapper.GroupBuyBoardMapper;
import kb.zango.domain.diary.mapper.HoneyTipBoardMapper;
import kb.zango.domain.diary.honeyTip.repository.SmallCategoryRepository;
import kb.zango.domain.users.entity.User;
import kb.zango.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HoneyTipBoardServiceImpl implements HoneyTipBoardService {

    private final BoardMapper boardMapper;
    private final GroupBuyBoardMapper groupBuyBoardMapper;
    private final HoneyTipBoardMapper honeyTipBoardMapper;
    private final SmallCategoryRepository smallCategoryRepository;
    private final UserRepository userRepository;
    private final GroupBuyBoardRepository groupBuyBoardRepository;
    private final BoardRepository boardRepository;

    @Override
    public Long insertHoneyTipBoard(HoneyTipBoardRequest request) {
        // 1. Board 엔티티 생성
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new kb.zango.global.exception
                        .ResourceNotFoundException("해당하는 유저를 찾을 수 없습니다: " + request.getUser_id()));
        Board board = new Board();
        board.setUser(user);
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        SmallCategory smallCategory = smallCategoryRepository.findByIdWithBigCategory(request.getSc_id());

        board.setSmallCategory(smallCategory);
        board.setRegiDate(LocalDateTime.now());

        // 2. Board 생성
        boardMapper.insertBoard(board);

        // 3. HoneyTipBoard 생성
//        HoneyTipBoard honeyTipBoard = new HoneyTipBoard(board);

        // 4. SmallCategory로 그룹 구매 여부 확인
        if (board.isGroupBuy()) {
            board.setBoard_type(1);
            GroupBuyBoard groupBuyBoard = new GroupBuyBoard(
                    null,
                    board.getBoardId(),
                    request.getGroupBuyItem(),
                    request.getReferenceSite(),
                    request.getPeopleLimit(),
                    request.getKakaoLink()
            );
            board.setBoard_type(0);
            groupBuyBoardMapper.insertGroupBuy(groupBuyBoard);
//            honeyTipBoard.setGroupBuyBoard(groupBuyBoard);
        }
        return board.getBoardId();
        // 5. HoneyTipBoard 생성
//        honeyTipBoardMapper.insertHoneyTipBoard(honeyTipBoard);
    }

    @Override
    public void updateHoneyTipBoard(HoneyTipBoardRequest request) {
        // 1. 기존 Board 조회
        Board board = boardMapper.findBoardById(request.getBoardId());

        SmallCategory smallCategory = smallCategoryRepository.findById(request.getSc_id())
                .orElseThrow(IllegalArgumentException::new);

        // 2. Board 수정
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setSmallCategory(smallCategory);
        board.setUpdateDate(LocalDateTime.now());

        // 3. 수정된 Board 저장
        boardMapper.updateBoard(board);

        // 4. HoneyTipBoard 수정
        HoneyTipBoard honeyTipBoard = honeyTipBoardMapper.getHoneyTipBoard(board.getBoardId());

        if (board.isGroupBuy()) {
            // 그룹 구매 관련 정보가 있다면
            if (honeyTipBoard.getGroupBuyBoard() == null) {
                // 그룹 구매 관련 정보가 없다면 새로 생성
                GroupBuyBoard groupBuyBoard = new GroupBuyBoard(
                        null,
                        board.getBoardId(),
                        request.getGroupBuyItem(),
                        request.getReferenceSite(),
                        request.getPeopleLimit(),
                        request.getKakaoLink()
                );
                honeyTipBoard.setGroupBuyBoard(groupBuyBoard);
            } else {
                // 이미 그룹 구매 관련 정보가 있다면 업데이트
                GroupBuyBoard groupBuyBoard = honeyTipBoard.getGroupBuyBoard();
                groupBuyBoard.setGroupBuyItem(request.getGroupBuyItem());
                groupBuyBoard.setReferenceSite(request.getReferenceSite());
                groupBuyBoard.setPeopleLimit(request.getPeopleLimit());
                groupBuyBoard.setKakaoLink(request.getKakaoLink());
            }
        } else {
            // 그룹 구매 관련 필드가 없다면 null 처리
            honeyTipBoard.setGroupBuyBoard(null);
        }

        // 5. 수정된 HoneyTipBoard 저장
        honeyTipBoardMapper.updateHoneyTipBoard(honeyTipBoard);
    }

    @Override
    public HoneyTipBoardResponse getHoneyTipBoard(Long boardId) {

        // 조회수 증가
        boardRepository.incrementViewCnt(boardId);

        // 1. Board 조회
//        Board board = boardMapper.findBoardById(boardId);
        Board board = boardRepository.findByBoardIdWithCategory(boardId);
        if (board.isGroupBuy()) {
            GroupBuyBoard groupBuyBoard = groupBuyBoardRepository.findByBoardId(board.getBoardId());
            return HoneyTipBoardResponse.HoneyTipWithGroupBuy(board, groupBuyBoard);
        }


        // 3. 응답 객체 생성 및 반환
        return HoneyTipBoardResponse.NormalHoneyTip(board);
    }

    @Override
    public void deleteHoneyTipBoard(Long boardId) {
        // 1. HoneyTipBoard 삭제
        honeyTipBoardMapper.deleteHoneyTipBoard(boardId);

        // 2. Board 삭제
        boardMapper.deleteBoard(boardId);
    }

    @Override
    public List<HoneyTipBoardResponse> getAllHoneyTipBoards(Long bigCategoryId) {
        // 1. 전체 게시글 목록 조회 (Board 테이블)
        List<Board> boards = boardRepository.findAllHoney(bigCategoryId);
        List<HoneyTipBoardResponse> result = new ArrayList<>();

        boards.stream().forEach(
                b->{
                    if (b.getBoard_type() == 0) {
                        result.add(HoneyTipBoardResponse.NormalHoneyTip(b));
                    } else {
                        GroupBuyBoard groupBuyBoard = groupBuyBoardRepository.findByBoardId(b.getBoardId());
                        result.add(HoneyTipBoardResponse.HoneyTipWithGroupBuy(b,groupBuyBoard));
                    }
                }
        );

        // 2. 각 게시글에 대해 HoneyTipBoard 정보를 조회하고, HoneyTipBoardResponse로 변환

        return result;
    }

    // 조회수순 상위 5개 조회
    @Override
    public List<HomeListHoneyBoardDTO> getPopularBoards() {
        List<Board> popularBoard = boardRepository.getPopularBoard();

        return popularBoard.stream()
                .map(board -> new HomeListHoneyBoardDTO(
                        board.getBoardId(),
                        board.getTitle(),
                        board.getUser().getUsername(),
                        board.getView_cnt(),
                        board.getLikeCnt()
                ))
                .collect(Collectors.toList()); // DTO 리스트로 수집
    }
}
