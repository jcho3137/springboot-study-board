package com.study.board.repository;

import com.study.board.entity.Board;
import com.study.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
