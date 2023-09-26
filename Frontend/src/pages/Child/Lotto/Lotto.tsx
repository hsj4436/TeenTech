import React, { useRef, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import LottoList from "../../../components/Lotto/LottoList";

const Lotto: React.FC = () => {
  const navigate = useNavigate();

  const ClickChange = () => {
    navigate("/LottoChange");
  };

  return (
    <div
      className="pt-20 max-h pb-3"
      style={{
        backgroundImage: "linear-gradient(180deg, #141E30 0%, #243B55 100%)",
      }}
    >
      <div className="flex justify-center">
        <div className="relative flex justify-center" style={{ width: "100%" }}>
          <img
            style={{ width: "auto", height: "200px" }}
            src="../../../src/assets/lotto/jackpot2.png"
          />
        </div>
      </div>
      <div className="flex justify-between mx-4">
        <div
          className="rounded-2xl w-1/2 mr-4 text-start"
          style={{
            position: "relative",
            backgroundColor: "#3EACFC",
            boxShadow: "0 0 2rem #3EACFC, inset 0 0 1.3rem #3EACFC",
          }}
          onClick={ClickChange}
        >
          <div className="text-2xl ml-3 mt-3 text-white">
            <div>복권 교환하기</div>
          </div>
          <img
            src="../../../src/assets/lotto/ticket2.png"
            style={{
              maxWidth: "80%",
              maxHeight: "80%",
              position: "absolute",
              bottom: 0,
              right: 10,
            }}
          />
        </div>

        <div className="justify-center w-1/2">
          <div
            className="rounded-2xl drop-shadow mb-3 text-xl"
            style={{ backgroundColor: "white" }}
          >
            <div className="pt-2 pl-3 text-start">당첨금</div>
            <div className="px-3 pt-4 pb-2 text-end">5,000원</div>
          </div>
          <div
            className="rounded-2xl drop-shadow text-xl"
            style={{ backgroundColor: "white" }}
          >
            <div className="pt-2 pl-3 text-start">복권 교환권</div>
            <div className="px-3 pt-4 pb-2 text-end">2장</div>
          </div>
        </div>
      </div>

      <div className="my-3 mx-4 pb-2 bg-white rounded-2xl">
        <LottoList />
      </div>
    </div>
  );
};

export default Lotto;