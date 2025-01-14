import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Board = ({ idx, title, contents, author }) => {
  const navigate = useNavigate(); //Url의 파라미터 값을 가져와서 이용하기위해

  const moveToUpdate = () => {
    navigate("/update/" + idx);
  };

  const deleteBoard = async () => {
    if (window.confirm("게시글을 삭제하시겠습니까?")) {
      await axios.delete(`//localhost:8080/board/${idx}`).then((res) => {
        alert("삭제되었습니다");
        navigate("/board");
      });
    }
  };

  const moveToList = () => {
    navigate("/board");
  };

  return (
    <div>
      <div>
        <h2>{title}</h2>
        <h2>{author}</h2>
        <hr />
        <p>{contents}</p>
      </div>
      <div>
        <button onClick={moveToUpdate}>수정</button>
        <button onClick={deleteBoard}>삭제</button>
        <button onClick={moveToList}>목록</button>
      </div>
    </div>
  );
};

export default Board;
