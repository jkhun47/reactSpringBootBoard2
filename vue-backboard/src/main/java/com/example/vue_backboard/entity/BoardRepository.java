package com.example.vue_backboard.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
//}
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findAllByOrderByIdxDesc(Pageable pageable);
}

