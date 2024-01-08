package com.boardexample.boarddemo.service;

import com.boardexample.boarddemo.repository.Board;
import com.boardexample.boarddemo.repository.BoardRepository;
import com.boardexample.boarddemo.repository.UpdateBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board) {
        board.setHits(0);
        board.setDate(LocalDate.now());
        return boardRepository.save(board);
    }

    public void update(Long boardId, UpdateBoardDto updateParam) {
        boardRepository.update(boardId, updateParam);
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

}
