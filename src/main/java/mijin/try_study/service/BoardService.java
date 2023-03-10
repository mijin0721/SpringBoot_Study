package mijin.try_study.service;

import lombok.RequiredArgsConstructor;
import mijin.try_study.dto.BoardDTO;
import mijin.try_study.entity.BoardEntity;
import mijin.try_study.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 3;
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.Direction.DESC, "id"));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // ?????? ???????????? ???????????? ???
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // ?????? ?????????
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB??? ????????? ????????? ??????
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // ?????? ????????? ??????
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // ??? ???????????? ???????????? ??? ??????
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // ?????? ????????? ?????? ??????
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // ??? ????????? ??????
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // ????????? ????????? ??????

        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(
              board.getId(), board.getBoardTitle(), board.getBoardHits(), board.getBoardCreatedTime()
        ));
        return boardDTOS;

    }
}
