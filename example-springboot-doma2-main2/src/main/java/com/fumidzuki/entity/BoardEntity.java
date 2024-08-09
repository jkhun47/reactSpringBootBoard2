package com.fumidzuki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seasar.doma.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BOARD")
@Entity(immutable = true)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;
//    @Column(name = "title")
    private String title;
//    @Column(name = "contents")
    private String contents;
//    @Column(name = "author")
    private String author;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
