package com.study.board.repository;

import com.study.board.entity.Board;
import com.study.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>
, SearchBoardRepository {

    @Query("select b, w from Board b left join b.writer w where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT b, r from Board b left join Reply r on r.board = b where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count(r)"+
    " from Board b "+
    " left join b.writer w " +
    " left join Reply r on r.board = b " +
    " group by b",
    countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("SELECT b, w, count(r)" +
    " FROM Board b left join b.writer w " +
    " left outer join Reply r on r.board = b" +
    " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
