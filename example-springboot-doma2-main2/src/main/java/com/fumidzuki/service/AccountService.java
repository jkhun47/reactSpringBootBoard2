package com.fumidzuki.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fumidzuki.dtos.BoardDto;
import com.fumidzuki.entity.BoardEntity;
import com.fumidzuki.page.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fumidzuki.dao.DatabaseOneDao;
import com.fumidzuki.entity.AccountOne;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
// @Transactional
public class AccountService {

  @Autowired
  DatabaseOneDao databaseOneDao;

  public List<AccountOne> getAccountOne() {
    return this.databaseOneDao.selectAll();
  }

//게시글 목록 가져오기
  public List<BoardEntity> getBoardAll() {
    return this.databaseOneDao.selectAllBoard();
    //        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
//    List<BoardDto> dtos = new ArrayList<>();
//    List<BoardEntity> boardEntities = databaseOneDao.selectAllBoard();
//    for(BoardEntity entity : boardEntities){
//      BoardDto dto = BoardDto.builder()
//              .idx(entity.getIdx())
//              .author(entity.getAuthor())
//              .title(entity.getTitle())
//              .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
//              .build();
//
//      dtos.add(dto);
//    }
//    return dtos;
  }

  //게시글 목록 가져오기(검색조건 있는 상태)
  public List<BoardEntity> getBoardLimit(Pagination page, String sk, String sv){
    //search key, search value세팅
    BoardEntity boardEntity = searchValueSet(sk,sv);
    SelectOptions options = SelectOptions.get().offset(page.getStartIndex()).limit(page.getPageSize());
//    if("author".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setAuthor(sv);
//      }
//    } else if ("title".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setTitle(sv);
//      }
//    } else if ("contents".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setContents(sv);
//      }
//    }
//    return this.databaseOneDao.selectAllLimit(options);
    return this.databaseOneDao.selectSearchLimit(options, boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
  }

  // 검색값이 입력된 목록 가져오기(검색조건 없는 상태)
  public List<BoardEntity> getSearchBoard(String sk, String sv){
    //search key, search value세팅
    BoardEntity boardEntity = searchValueSet(sk,sv);
//    BoardEntity boardEntity = new BoardEntity();
//    if("author".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setAuthor(sv);
//      }
//    } else if ("title".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setTitle(sv);
//      }
//    } else if ("contents".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        boardEntity.setContents(sv);
//      }
//    }
    return this.databaseOneDao.selectSearchBoard(boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
  }

  //게시글 가져오기
  public BoardEntity getBoard(Long id) {
    return this.databaseOneDao.selectById(id);
  }

  //게시글 가져오기(컨트롤러에서 분리)
  public BoardDto getListResult(int page, String sk, String sv){
    List<BoardEntity> datas = getBoardAll();
    List<BoardEntity> searchDatas = getSearchBoard(sk,sv);
    int listCnt = datas.size();
    int searchListCnt = searchDatas.size();
//    int curPage = Integer.parseInt(page);
    int curPage = page;

    //search key, search value세팅
    BoardEntity boardEntity = searchValueSet(sk,sv);

    Pagination pagination = null;
//    getPagination(pagination,sk,sv,searchListCnt,listCnt,curPage);
    if(!sk.isEmpty() && !sv.isEmpty()){
      pagination = new Pagination(searchListCnt,curPage,10,10);
    } else {
      pagination = new Pagination(listCnt,curPage,10,10);
    }

    // 한페이지당 표시 개수 처리 옵션
    SelectOptions options = SelectOptions.get().offset(pagination.getStartIndex()).limit(pagination.getPageSize());

    // listResult와 pagination 2값을 리턴하기위해 boardDto에 삽입(set)
    List<BoardEntity> listResult = this.databaseOneDao.selectSearchLimit(options, boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
      BoardDto boardDto = new BoardDto();
      boardDto.setPage(pagination);
      boardDto.setList(listResult);

    return boardDto;
//    return this.databaseOneDao.selectSearchLimit(options, boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
  }

//search key, search value를 검색하기전 boardEnttity에 세팅
  public BoardEntity searchValueSet(String sk, String sv){
    BoardEntity boardEntity = new BoardEntity();
    if("author".equals(sk)) {
      if(StringUtils.hasLength(sv)) {
        boardEntity.setAuthor(sv);
      }
    } else if ("title".equals(sk)) {
      if(StringUtils.hasLength(sv)) {
        boardEntity.setTitle(sv);
      }
    } else if ("contents".equals(sk)) {
      if(StringUtils.hasLength(sv)) {
        boardEntity.setContents(sv);
      }
    }
    return boardEntity;
  }

//  public Pagination getPagination(Pagination page,String sk,String sv,int searchListCnt, int listCnt, int curPage ){
//
//    if(!sk.isEmpty() && !sv.isEmpty()){
//      page = new Pagination(searchListCnt,curPage,10,10);
//    } else {
//      page = new Pagination(listCnt,curPage,10,10);
//    }
//    return page;
//  }

  //게시글 등록
//  public Result<BoardEntity> addBoard(BoardDto boardDto){
//    BoardEntity entity = BoardEntity.builder()
//            .title(boardDto.getTitle())
//            .contents(boardDto.getContents())
//            .author(boardDto.getAuthor())
//            .createdAt(LocalDateTime.now())
//            .build();
//    return this.databaseOneDao.addBoard(entity);
//  }
  public Result<BoardEntity> register(BoardDto boardDto){
        BoardEntity entity = BoardEntity.builder()
            .title(boardDto.getTitle())
            .contents(boardDto.getContents())
            .author(boardDto.getAuthor())
            .createdAt(LocalDateTime.now())
            .build();
    return this.databaseOneDao.insert(entity);
  }

  //게시글 수정
//  public Result<BoardEntity> modify(BoardDto boardDto) {
//    BoardEntity entity = BoardEntity.builder()
//            .title(boardDto.getTitle())
//            .contents(boardDto.getContents())
//            .createdAt(LocalDateTime.now())
//            .build();
//    return this.databaseOneDao.update(entity);
//  }

  public Result<BoardEntity> modify(BoardDto boardDto) {
    BoardEntity entity = this.databaseOneDao.selectById(boardDto.getIdx());
    entity = BoardEntity.builder()
            .idx(boardDto.getIdx())
            .title(boardDto.getTitle())
            .contents(boardDto.getContents())
            .author(entity.getAuthor())
            .createdAt(entity.getCreatedAt())
            .build();
//    return this.databaseOneDao.updateBoard(entity);
    return this.databaseOneDao.update(entity);
  }


  // public void getAccount() {
  //
  // List<AccountOne> accountOnes = this.databaseOneDao.selectAll();
  // log.info("1：取得件数：{}", accountOnes.size());
  // AccountOne one = new AccountOne();
  // one.setUserId(new Random().nextInt());
  // one.setUsername("username_" + one.getUserId());
  // this.databaseOneDao.insert(one);
  //
  // List<AccountTwo> accountTwos = this.databaseTwoDao.selectAll();
  // log.info("2：取得件数：{}", accountTwos.size());
  //
  // // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
  //
  // }

  //게시글 목록 가져오기(페이징처리 추가)
//    public Header<List<BoardDto>> getBoardList(Pageable pageable, SearchCondition searchCondition){
//        List<BoardDto> dtos = new ArrayList<>();
////        List<BoardEntity> boardEntities = boardRepository.findAll();
////        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
//        Page<BoardEntity> boardEntities = boardRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
//        for(BoardEntity entity : boardEntities){
//            BoardDto dto = BoardDto.builder()
//                    .idx(entity.getIdx())
//                    .author(entity.getAuthor())
//                    .title(entity.getTitle())
//                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
//                    .build();
//
//            dtos.add(dto);
//        }
//        Pagination pagination = new Pagination(
//                (int) boardEntities.getTotalElements()
//                , pageable.getPageNumber() + 1
//                , pageable.getPageSize()
//                , 10
//        );
//        return Header.OK(dtos, pagination);
//    }

}
