// import logo from './logo.svg';
import "./App.css";
import { Route, Routes } from "react-router-dom";
import BoardList from "./routes/BoardList";
import Home from "./routes/Home";
import React from "react";
import ReactHome from "./routes/ReactHome";
import BoardDetail from "./routes/BoardDetail";
import BoardWrite from "./routes/BoardWrite";
import BoardUpdate from "./routes/BoardUpdate";

function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
    // <div>
    //   컨텐츠 요소가 표시됩니다.
    // </div>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/reacthome" element={<ReactHome />} />
      <Route path="/board" element={<BoardList />} />
      <Route path="/board/:idx" element={<BoardDetail />} />
      <Route path="/write" element={<BoardWrite />} />
      <Route path="/update/:idx" element={<BoardUpdate />} />
    </Routes>
  );
}

export default App;
