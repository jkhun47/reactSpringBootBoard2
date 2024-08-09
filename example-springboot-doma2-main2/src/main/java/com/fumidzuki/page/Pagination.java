package com.fumidzuki.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

    //페이지당 보여지는 게시글 최대 개수
    private int pageSize;

    //현재페이지
    int page;

    //현재블럭
    int block;

    //총 게시글 수
    int totalListCnt;

    //총 페이지 수
    int totalPageCnt;

    //총 구간 수
    int totalBlockCnt;

    //시작 페이지
    int startPage;

    //마지막 페이지
    int endPage;

    //이전 구간 마지막 페이지
    int prevBlock;

    //다음 구간 시작 페이지
    int nextBlock;

    //인덱스
    int startIndex;

    public Pagination(Integer totalListCnt, Integer page, Integer pageSize, Integer blockSize){
        this.pageSize = pageSize;

        //현재 페이지
        this.page = page;

        //총 게시글 수
        this.totalListCnt = totalListCnt;

        //총 페이지 수
        totalPageCnt = (int) Math.ceil(totalListCnt * 1.0/ this.pageSize);

        //총 블럭 수
        totalBlockCnt = (int) Math.ceil(totalPageCnt * 1.0/ blockSize);

        //현재 블럭
        block = (int) Math.ceil((this.page * 1.0) / blockSize);

        //블럭 시작 페이지
        startPage = ((block - 1 ) * blockSize + 1);

        //블럭 마지막 페이지
        endPage = startPage + blockSize - 1;

        //블럭 마지막 페이지 validaton
        if(endPage > totalPageCnt) endPage = totalPageCnt;

        //이전 블럭 (클릭시, 이전 블럭 마지막 페이지)
        prevBlock = (block * blockSize) - blockSize;

        //이전 블럭 validation
        if(prevBlock < 1) prevBlock = 1;

        //다음블럭 (클릭시, 다음 블럭 첫번째 페이지)
        nextBlock = (block * blockSize + 1);

        //다음블럭 validation
        if(nextBlock > totalPageCnt) nextBlock = totalPageCnt;

        startIndex = (this.page - 1) * this.pageSize;
    }
//@Data
//public class Pagination {
//
//    /** 한 페이지당 게시글 수 **/
//    private int pageSize = 10;
//
//    /** 한 블럭(range)당 페이지 수 **/
//    private int rangeSize = 10;
//
//    /** 현재 페이지 **/
//    private int curPage = 1;
//
//    /** 현재 블럭(range) **/
//    private int curRange = 1;
//
//    /** 총 게시글 수 **/
//    private int listCnt;
//
//    /** 총 페이지 수 **/
//    private int pageCnt;
//
//    /** 총 블럭(range) 수 **/
//    private int rangeCnt;
//
//    /** 시작 페이지 **/
//    private int startPage = 1;
//
//    /** 끝 페이지 **/
//    private int endPage = 1;
//
//    /** 시작 index **/
//    private int startIndex = 0;
//
//    /** 이전 페이지 **/
//    private int prevPage;
//
//    /** 다음 페이지 **/
//    private int nextPage;
//
//    public Pagination(int listCnt, int curPage){
//
//        /**
//         * 페이징 처리 순서
//         * 1. 총 페이지수
//         * 2. 총 블럭(range)수
//         * 3. range setting
//         */
//
//        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.
//        /** 현재페이지 **/
//        setCurPage(curPage);
//        /** 총 게시물 수 **/
//        setListCnt(listCnt);
//
//        /** 1. 총 페이지 수 **/
//        setPageCnt(listCnt);
//        /** 2. 총 블럭(range)수 **/
//        setRangeCnt(pageCnt);
//        /** 3. 블럭(range) setting **/
//        rangeSetting(curPage);
//
//        /** DB 질의를 위한 startIndex 설정 **/
//        setStartIndex(curPage);
//    }
//
//    public void setPageCnt(int listCnt) {
//        this.pageCnt = (int) Math.ceil(listCnt*1.0/pageSize);
//    }
//    public void setRangeCnt(int pageCnt) {
//        this.rangeCnt = (int) Math.ceil(pageCnt*1.0/rangeSize);
//    }
//    public void rangeSetting(int curPage){
//
//        setCurRange(curPage);
//        this.startPage = (curRange - 1) * rangeSize + 1;
//        this.endPage = startPage + rangeSize - 1;
//
//        if(endPage > pageCnt){
//            this.endPage = pageCnt;
//        }
//
//        this.prevPage = curPage - 1;
//        this.nextPage = curPage + 1;
//    }
//    public void setCurRange(int curPage) {
//        this.curRange = (int)((curPage-1)/rangeSize) + 1;
//    }
//    public void setStartIndex(int curPage) {
//        this.startIndex = (curPage-1) * pageSize;
//    }
}
