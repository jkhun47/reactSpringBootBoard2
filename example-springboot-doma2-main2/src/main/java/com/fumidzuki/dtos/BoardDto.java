package com.fumidzuki.dtos;

import com.fumidzuki.entity.BoardEntity;
import com.fumidzuki.page.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;

    //service에서 controller로 복수의 값을 리턴하기 위해
    private List<BoardEntity> list;
    private Pagination page;
}
