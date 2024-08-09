package com.example.vue_backboard.services;

import com.example.vue_backboard.dtos.BoardDto;
import com.example.vue_backboard.entity.BoardEntity;
import com.example.vue_backboard.entity.BoardRepository;
import com.example.vue_backboard.entity.BoardRepositoryCustom;
import com.example.vue_backboard.model.Header;
import com.example.vue_backboard.model.Pagination;
import com.example.vue_backboard.model.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardRepositoryCustom boardRepositoryCustom;

    //게시글 목록 가져오기
//    public List<BoardDto> getBoardList(){
//        List<BoardEntity> boardEntities = boardRepository.findAll();
//        List<BoardDto> dtos = new ArrayList<>();
//        for(BoardEntity entity:boardEntities){
//            BoardDto dto = BoardDto.builder()
//                    .idx(entity.getIdx())
//                    .author(entity.getAuthor())
//                    .title(entity.getTitle())
//                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
//                    .build();
//
//            dtos.add(dto);
//        }
//        return dtos;
//    }
    //게시글 가져오기
    public BoardDto getBoard(Long id){
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다"));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .author(entity.getAuthor())
                .build();
    }

    //게시글 등록
    public BoardEntity create(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.builder()
                .idx(boardDto.getIdx())
                .title(boardDto.getTitle())
                .author(boardDto.getAuthor())
                .contents(boardDto.getContents())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(entity);
    }

    //게시글 수정
    public BoardEntity update(BoardDto boardDto) {
        BoardEntity entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다"));
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }

    //게시글 목록 가져오기(페이징처리 추가)
    public Header<List<BoardDto>> getBoardList(Pageable pageable, SearchCondition searchCondition){
        List<BoardDto> dtos = new ArrayList<>();
//        List<BoardEntity> boardEntities = boardRepository.findAll();
//        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
        Page<BoardEntity> boardEntities = boardRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        for(BoardEntity entity : boardEntities){
            BoardDto dto = BoardDto.builder()
                    .idx(entity.getIdx())
                    .author(entity.getAuthor())
                    .title(entity.getTitle())
                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }
        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(dtos, pagination);
    }

}
