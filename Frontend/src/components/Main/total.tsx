import React from "react";
import Credit from "../Credit";

type Props = {
  id: number | null;
};

const Total: React.FC<Props> = ({ id }) => {
  return (
    <div className="mx-2 pt-2">
      {/* <div className=" text-start	">{id}규렬이 코 묻은 돈</div> */}
      {/* <div className="rounded-2xl	" style={{ backgroundColor: "#D8E6EE" }}>
        <div className="text-start p-3">용돈 (쓸 수 있는 돈)</div>
        <div className="text-end p-3">170,000원</div>
      </div> */}
      {/* <div className="flex justify-center">
        <div
          className="w-1/2 mr-2 rounded-2xl	"
          style={{ backgroundColor: "#D8E6EE" }}
        >
          <div className="pt-2 pl-3 text-start">예금</div>
          <div className="px-3 pt-3 pb-2 text-end">
            <div>+얼마</div>
            <div>10,500</div>
          </div>
        </div>
        <div
          className="w-1/2 ml-2 rounded-2xl"
          style={{ backgroundColor: "#D8E6EE" }}
        >
          <div>
            <div className="pt-2 pl-3 text-start">주식</div>
            <div className="px-3 text-end pt-3">
              +0.2%
              <div className="flex justify-end">
                <div>상승</div>
                <div>8,200</div>
              </div>
            </div>
          </div>
        </div>
      </div> */}
      <div
        className="rounded-2xl drop-shadow-lg	mb-3"
        style={{
          backgroundColor: "#FF76BA",
        }}
      >
        <div className="text-2xl pt-2 pl-3 text-start text-white	">
          나의 예금 내역 확인하기
        </div>
        <div>
          <div className=" pl-3 text-start text-white">
            얼마가 모였는지 확인해 볼까요?
          </div>
          <div className="px-3 pb-2 text-end">
            <div className="text-white text-xl">+얼마</div>
            <div className="text-white text-xl">10,500</div>
          </div>
        </div>
      </div>

      <div
        className="rounded-2xl drop-shadow-lg	mb-3"
        style={{
          backgroundColor: "#7AC2E7",
        }}
      >
        <div className="text-2xl pt-2 pl-3 text-start text-white	">
          나의 예금 내역 확인하기
        </div>
        <div>
          <div className="pl-3 text-start text-white">
            얼마가 모였는지 확인해 볼까요?
          </div>
          <div className="px-3 pb-2 text-end">
            <div className="text-white text-xl">+얼마</div>
            <div className="text-white text-xl">10,500</div>
          </div>
        </div>
      </div>

      {/* <div>
        <div className="my-3" style={{ backgroundColor: "green" }}>
          <Credit />
        </div>
      </div> */}
      <div className="flex justify-center">
        <div
          className="w-1/2 mr-2 rounded-2xl"
          style={{ backgroundColor: "#D8E6EE" }}
        >
          <div className="pt-2 pl-3 text-start">대출잔액</div>
          <div className="px-3 pt-4 pb-2 text-end">대출잔액</div>
        </div>
        <div
          className="w-1/2 ml-2 rounded-2xl"
          style={{ backgroundColor: "#D8E6EE" }}
        >
          <div className="pt-2 pl-3 text-start">대출 상환일</div>
          <div className="px-3 pt-4 pb-2 text-end">D - 10</div>
        </div>
      </div>
    </div>
  );
};

export default Total;
