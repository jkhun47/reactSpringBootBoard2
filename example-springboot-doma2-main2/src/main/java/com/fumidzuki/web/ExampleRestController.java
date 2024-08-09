package com.fumidzuki.web;

import java.util.List;

import com.fumidzuki.dtos.BoardDto;
import com.fumidzuki.entity.BoardEntity;
import com.fumidzuki.model.Header;
import com.fumidzuki.page.Pagination;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fumidzuki.entity.AccountOne;
import com.fumidzuki.service.AccountService;

//@Slf4j
//@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/board")
public class ExampleRestController {

  @Autowired
  AccountService accountService;

  @GetMapping("one")
  public List<AccountOne> one() {
    return this.accountService.getAccountOne();
  }

  @GetMapping("list")
//  public List<BoardDto> boardList() {
//    List<BoardDto> datas = this.accountService.getBoardAll();
//    return datas;

//  public List<BoardEntity> boardList() {
//    List<BoardEntity> datas = this.accountService.getBoardAll();
//    return datas;
//  }

//  public Header<List<BoardEntity>> boardList(@RequestParam String page, @RequestParam String sk, @RequestParam String sv) {
//  public Header<List<BoardEntity>> boardList(@RequestParam int page, @RequestParam String sk, @RequestParam String sv, @RequestParam(defaultValue = "10") int size, Search search) {
  public Header<List<BoardEntity>> boardList(@RequestParam int page, @RequestParam String sk, @RequestParam String sv) {
//    List<BoardEntity> datas = this.accountService.getBoardAll();
//    List<BoardEntity> searchDatas = this.accountService.getSearchBoard(sk,sv);
//    int listCnt = datas.size();
//    int searchListCnt = searchDatas.size();
//    int curPage = Integer.parseInt(page);
//    Pagination pagination = null;
//    if(!sk.isEmpty() && !sv.isEmpty()){
//      pagination = new Pagination(searchListCnt,curPage,10,10);
//    }else{
//      pagination = new Pagination(listCnt,curPage,10,10);
//    }
//    List<BoardEntity> limitDatas = this.accountService.getBoardLimit(pagination,sk,sv);
    BoardDto listResult = this.accountService.getListResult(page,sk,sv);
//    BoardDto listResult = this.accountService.getListResult(page,search.getSk(),search.getSv());
    List<BoardEntity>  limitDatas = listResult.getList();
    Pagination pagination = listResult.getPage();
//    return Header.OK(result,pagination);
    return Header.OK(limitDatas,pagination);
  }

  @GetMapping("{id}")
  public BoardEntity getBoard(@PathVariable Long id){
    BoardEntity data = this.accountService.getBoard(id);
//    return this.accountService.getBoard(id);
    return data;
  }

  @PostMapping("")
    public Result<BoardEntity> create(@RequestBody BoardDto boardDto){
    Result<BoardEntity> data = accountService.register(boardDto);
    return data;
  }
  //  @PostMapping("")
//  public Result<BoardEntity> create(@RequestBody BoardDto boardDto){
//    Result<BoardEntity> data = accountService.addBoard(boardDto);
////    return accountService.addBoard(boardEntity);
//    return data;
//  }
  @PatchMapping("")
    public Result<BoardEntity> update(@RequestBody BoardDto boardDto){
    Result<BoardEntity> data = accountService.modify(boardDto);
    return data;
  }

//  @PatchMapping("")
//    public Result<BoardEntity> update(@RequestBody BoardDto boardDto){
//    Result<BoardEntity> data = accountService.modify(boardDto);
//    return data;
//  }
}
