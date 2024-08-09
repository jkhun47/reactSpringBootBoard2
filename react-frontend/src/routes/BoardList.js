import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "../components/common.css";

// import { Form, Button, Container, Row, Col } from "react-bootstrap";

const BoardList = () => {
  const navigate = useNavigate();
  const [boardList, setBoardList] = useState([]);
  const [pageList, setPageList] = useState([]);

  const [curPage, setCurPage] = useState(0); //현재페이지세팅
  const [prevBlock, setPrevBlock] = useState(0); //이전 페이지 블록
  const [nextBlock, setNextBlock] = useState(0); //다음 페이지 블록
  const [lastPage, setLastPage] = useState(0); //마지막 페이지

  const [search, setSearch] = useState({
    page: 1,
    sk: "",
    sv: "",
  });

  const getBoardList = () => {
    if (search.page === curPage) return; //현재 페이지와 누른 페이지가 같으면 return

    // const queryString = Object.entries(search)
    //   .map((e) => e.join("="))
    //   .join("&");

    const resp =
      // await axios.get("//localhost:8080/board/list?" + queryString)
      axios.get("//localhost:8080/employees/list?" + search).data; // 2.게시글 목록 데이터 할당
    setBoardList(resp.data); // 3.boardList 변수에 할당

    const pngn = resp.pagination;

    // const { endPage, nextBlock, prevBlock, startPage, totalPageCnt } = pngn;
    const { end_page, next_block, prev_block, start_page, total_page_cnt } =
      pngn;

    setCurPage(search.page);
    setPrevBlock(prev_block);
    setNextBlock(next_block);
    setLastPage(total_page_cnt);

    const tmpPages = [];
    for (let i = start_page; i <= end_page; i++) {
      tmpPages.push(i);
    }

    setPageList(tmpPages);
  };

  const moveToWrite = () => {
    navigate("/write");
  };

  const onClick = (event) => {
    let value = event.target.value;
    setSearch({
      ...search,
      page: value,
    });
    getBoardList();
  };

  const onChange = (event) => {
    const { value, name } = event.target;
    setSearch({
      ...search,
      [name]: value,
    });
  };

  const onSearch = () => {
    if (search.sk !== "" && search.sv !== "") {
      setSearch({
        ...search,
        page: 1,
      });
      setCurPage(0);
      getBoardList();
    }
  };

  useEffect(() => {
    getBoardList(); //1.게시글 목록 조회 함수 호출
  }, [search]);

  return (
    <div class="board-list">
      {/* 게시판 목록 출력 */}
      <button class="writebtn" onClick={moveToWrite}>
        글쓰기
      </button>
      <table id="customers">
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>성별</th>
            <th>생일</th>
            <th>입사일</th>
          </tr>
        </thead>
        <tbody>
          {boardList.map((board) => {
            return (
              <tr>
                <td>
                  <Link to={`/board/${board.emp_no}`}>{board.emp_no}</Link>
                </td>
                <td>
                  {board.first_name}&nbsp;{board.last_name}
                </td>
                <td>{board.gender}</td>
                <td>{board.birth_date}</td>
                <td>{board.hire_date}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
      {/* <ul>
        {boardList.map((board) => (
          <li key={board.idx}>
            <Link to={`/board/${board.idx}`}>{board.title}</Link>
          </li> // 4.map 함수로 데이터 출력
        ))}
      </ul> */}
      <div class="pagination">
        <a onClick={onclick} value={1}>
          &lt;&lt;
        </a>
        <a onClick={onclick} value={prevBlock}>
          &lt;
        </a>
        {pageList.map((page, index) => (
          <a key={index} onClick={onClick} value={page}>
            {page}
          </a>
        ))}
        <a click={onClick} value={nextBlock}>
          &gt;
        </a>
        <a click={onClick} value={lastPage}>
          &gt;&gt;
        </a>
      </div>
      <br />
      <div>
        <select name="sk" onChange={onChange}>
          <option value="">선택</option>
          <option value="title">제목</option>
          <option value="contents">내용</option>
        </select>
        &nbsp;&nbsp;
        <input type="text" name="sv" id="" onChange={onChange} />
        &nbsp;&nbsp;
        <button class="searchbtn" onClick={onSearch}>
          검색
        </button>
        {/* <div class="dropdown">
          <button class="dropbtn">선택</button>
          <div class="dropdown-content">
            <a value="title">제목</a>
            <a value="contents">내용</a>
          </div>
          <input type="text" name="sv" id="" onChange={onChange} />
          <button class="searchbtn" onClick={onSearch}>
            검색
          </button>
        </div> */}
      </div>
      {/* <div>
        <select name="sk" onChange={onChange}>
          <option value="">-선택-</option>
          <option value="title">제목</option>
          <option value="contents">내용</option>
        </select>
        <input type="text" name="sv" id="" onChange={onChange} />
        <button onClick={onSearch}>검색</button>
      </div> */}
      {/* <br />
      <div>
        <button onClick={moveToWrite}>글쓰기</button>
      </div> */}
    </div>
  );
};

export default BoardList;
