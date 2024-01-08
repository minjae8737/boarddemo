package com.boardexample.boarddemo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class BoardRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public BoardRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("id");
    }

    public Board save(Board board) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(board);
        Number key = jdbcInsert.executeAndReturnKey(param);
        board.setId(key.longValue());
        return board;
    }

    public void update(Long boardId, UpdateBoardDto updateParam) {
        String sql = "update board " +
                "set title=:title, content=:content " +
                "where id=:id";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", updateParam.getTitle())
                .addValue("content", updateParam.getContent())
                .addValue("id", boardId);
        template.update(sql, param);

    }

    public Optional<Board> findById(Long id) {
        String sql = "select * " +
                "from board " +
                "where id = :id";

        return Optional.of(new Board());
    }

    public List<Board> findAll() {
        String sql = "select * " +
                "from board";
        return new ArrayList<Board>();
    }

}
