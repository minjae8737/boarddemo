package com.boardexample.boarddemo.service;

import com.boardexample.boarddemo.repository.Board;
import com.boardexample.boarddemo.repository.BoardRepository;
import com.boardexample.boarddemo.repository.UpdateBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(Board board) {
        boardRepository.save(board);
        return new Board();
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
