import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const BoardUpdate = () => {
  const navigate = useNavigate();
  const { idx } = useParams();

  const [board, setBoard] = useState({
    idx: 0,
    title: "",
    author: "",
    contents: "",
  });

  const { title, author, contents } = board; //비구조화 할당

  const getBoard = async () => {
    const resp = await (await axios.get(`//localhost:8080/board/${idx}`)).data;
    setBoard(resp);
  };

  const onChange = (event) => {
    const { value, name } = event.target; //event.target에서 name과 value만 가져오기
    setBoard({
      ...board,
      [name]: value,
    });
  };
  const updateBoard = async () => {
    await axios.patch(`//localhost:8080/board`, board).then((res) => {
      alert("수정되었습니다");
      navigate("/board/" + idx);
    });
  };
  const backToDetail = () => {
    navigate("/board/" + idx);
  };

  useEffect(() => {
    getBoard();
  }, []);
  return (
    <div>
      <div>
        <span>제목</span>
        <input type="text" name="title" value={title} onChange={onChange} />
      </div>
      <br />
      <div>
        <span>작성자</span>
        <input type="text" name="author" value={author} readonly={true} />
      </div>
      <br />
      <div>
        <span>내용</span>
        <textarea
          name="contents"
          conls="30"
          rows="10"
          value={contents}
          onChange={onChange}
        />
      </div>
      <br />
      <div>
        <button onClick={updateBoard}>수정</button>
        <button onClick={backToDetail}>취소</button>
      </div>
    </div>
  );
};

export default BoardUpdate;
