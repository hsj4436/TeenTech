import React from "react";
import Credit from "../../../components/Credit";
import LoanStatus from "../../../components/Loan/LoanStatus";
import { Link } from "react-router-dom";
import LoanList from "../../../components/Loan/LoanList";
import LoanCreate from "../../../components/Loan/LoanCreate";
import LoanrepaymentButton from "../../../components/Loan/LoanrepaymentButton";

const Loan: React.FC = () => {
  const [open, setOpen] = React.useState(0);

  const handleOpen = (value) => setOpen(open === value ? 0 : value);

  return (
    <div className="pt-24">
      <Credit>

      </Credit>
      <div className="flex flex-col justify-center">
        <LoanStatus>
          <div className="flex justify-end">
            <button
              onClick={() => handleOpen(1)}
              className="font-bold p-2 pt-1 pb-1 ml-6 mr-6 mt-2 mb-2"
              style={{ color: "#363636", backgroundColor: "#ABC3D0" }}
            >
              대출 신청
            </button>
          </div>
        </LoanStatus>
      </div>
      <Link to="/LoanCompleted">
        <p className="text-xs text-end mr-10 text-black">지난 대출 보기</p>
      </Link>
      <hr className="border-1" style={{ borderColor: "#ABD0CE" }} />
      <div>
        <p className="m-4 text-lg">대출 리스트</p>
      </div>
      <div>
        <LoanList
          children2={
            <div className="border-2 border-black rounded-md">
              <div className="p-6 pt-1 pb-1">
                <LoanrepaymentButton></LoanrepaymentButton>
              </div>
            </div>
          }
        >
          <div className="border-2 rounded-md pl-2 pr-2 mr-4 mb-1 bg-gray-300">
            <LoanrepaymentButton></LoanrepaymentButton>
          </div>
        </LoanList>
        <LoanList children2>
          <div className="border-2 rounded-md pl-2 pr-2 mr-4 mb-1 bg-gray-300">
            <LoanrepaymentButton></LoanrepaymentButton>
          </div>
        </LoanList>
        <LoanList children2>
          <div className="border-2 rounded-md pl-2 pr-2 mr-4 mb-1 bg-gray-300">
            <LoanrepaymentButton></LoanrepaymentButton>
          </div>
        </LoanList>
      </div>
      {open === 1 && <LoanCreate closeModal={handleOpen}></LoanCreate>}
    </div>
  );
};

export default Loan;
