import React from "react";
import Statics from "../../../components/AccountBook/Statics";
import AccountBotton from "../../../components/AccountBook/AccountButton";

const Data = [
  {
    case1: "마트",
    case2: "씨유",
    case3: -2000,
    case4: "욕구",
  },
  {
    case1: "퀴즈",
    case2: "퀴즈용돈",
    case3: 1000,
    case4: "수입",
  },
  {
    case1: "음료",
    case2: "할리스",
    case3: -5500,
    case4: "욕구",
  },
];

const AccountBookDetail: React.FC = () => {
  return (
    <div>
      <div style={{ width: "100%", paddingTop: "50px" }}>
        <Statics />
      </div>
      <div style={{ backgroundColor: "white" }}>
        <AccountBotton />
        <div
          style={{
            width: "100%",
            height: "1px",
            backgroundColor: "pink",
            marginTop: "10px",
            marginBottom: "10px",
          }}
        ></div>
        <div
          style={{
            display: "flex",
            color: "black",
            justifyContent: "space-around",
          }}
        >
          <div>카테고리</div>
          <div>구체적내용</div>
          <div>소비금액</div>
          <div>소비유형</div>
        </div>
        {Data.map((item, index) => (
          <div
            key={index}
            style={{
              display: "flex",
              justifyContent: "space-around",
              color: "black",
            }}
          >
            <div>{item.case1}</div>
            <div>{item.case2}</div>
            <div style={{ color: item.case3 < 0 ? "red" : "blue" }}>
              {item.case3}
            </div>
            <div>{item.case4}</div>
          </div>
        ))}
      </div>
      <h2>가계부 날짜 상세 페이지</h2>
    </div>
  );
};

export default AccountBookDetail;
