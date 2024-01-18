package com.boardexample.boarddemo.service;

import com.boardexample.boarddemo.domain.Board;
import com.boardexample.boarddemo.domain.BoardSearchDto;
import com.boardexample.boarddemo.domain.UpdateBoardDto;
import com.boardexample.boarddemo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board) {
        board.setHits(0);
        board.setDate(LocalDateTime.now());
        return boardRepository.save(board);
    }

    public void update(Long boardId, UpdateBoardDto updateParam) {
        boardRepository.update(boardId, updateParam);
    }

    public Optional<Board> findById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public List<Board> findBoards(BoardSearchDto boardSearchDto) {

        if (boardSearchDto.getSearchWord() == null) {
            List<Board> boards = boardRepository.findAll();
            Collections.reverse(boards);
            return boards;
        } else {
            List<Board> boards = boardRepository.findBySearchWord(boardSearchDto);
            Collections.reverse(boards);
            return boards;
        }
    }

//    public long getBoardCount() {
//        long boardCount = boardRepository.getBoardCount();
//        return boardCount;
//    }

    public void deleteById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

//    public List<Board> findAll() {
//        List<Board> boards = boardRepository.findAll();
//        Collections.reverse(boards);
//        return boards;
//    }

    public void addHits(Long boardId, int hits) {
        boardRepository.addHits(boardId, hits);
    }
}
