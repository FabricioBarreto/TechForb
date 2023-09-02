"use client";
import { useEffect, useState } from "react";
import useAuthHeader from "../hooks/useAuthHeader";
import useAuth from "@/app/hooks/useAuth";
import axios from "axios";
import { useRouter } from "next/navigation";
import jwt from "jsonwebtoken";
import { ArrowLeftIcon } from "@heroicons/react/24/solid";
import { CircularProgress } from "@mui/material";

export default function Transaction() {
  useAuthHeader();
  useAuth();

  const router = useRouter();

  const [senderCardList, setSenderCardList] = useState([
    {
      cardNumber: "",
      amount: "",
      dateOdExp: "",
      owner: "",
    },
  ]);

  const [transactionData, setTransactionData] = useState({
    senderCard: "",
    receiverCard: "",
    amount: "",
  });

  let cardSelect;
  const [amount, setAmount] = useState("");

  const token = localStorage.getItem("token");

  const getSenderCards = async () => {
    if (token) {
      const decodedToken = jwt.decode(token);
      const userEmail = decodedToken ? decodedToken.sub : null;
      const res = await axios.get(
        `http://localhost:8080/api/cards/${userEmail}`
      );
      setSenderCardList(res.data);
      setTransactionData({
        ...transactionData,
        senderCard: res.data[0].cardNumber,
      });
      setAmount(res.data[0].amount);
    }
  };

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    getSenderCards().then(() => {
      setLoading(false);
    });
  }, []);

  const handleSenderInfo = (e: any) => {
    //
    setTransactionData({
      ...transactionData,
      senderCard: e.target.value,
    });

    cardSelect = senderCardList.find(
      (card) => card.cardNumber === e.target.value
    );

    if (cardSelect) {
      setAmount(cardSelect.amount);
    }
  };

  const handleNumberReceiverCard = (e: any) => {
    setTransactionData({
      ...transactionData,
      receiverCard: e.target.value,
    });
  };

  const handleAmount = (e: any) => {
    setTransactionData({
      ...transactionData,
      amount: e.target.value,
    });
  };

  const makeTransaction = async () => {
    setLoading(true);

    await axios.post("http://localhost:8080/api/transaction", transactionData);

    router.push("/");

    setLoading(false);
  };

  const comeBack = () => {
    router.back();
  };

  return (
    <div className="min-h-screen bg-gray-100 py-6 flex flex-col justify-center sm:py-12">
      <div className="w-40">{loading ? <CircularProgress /> : ""}</div>
      <div className="bg-black inline-block absolute top-5 left-5">
        <ArrowLeftIcon
          className="w-8 bg-blue-500 cursor-pointer"
          onClick={comeBack}
        ></ArrowLeftIcon>
      </div>
      <div className="relative py-3 sm:max-w-xl sm:mx-auto">
        <div className="relative px-4 py-10 bg-white mx-8 md:mx-0 shadow rounded-3xl sm:p-10">
          <div className="max-w-md mx-auto">
            <div className="flex items-center space-x-5">
              <div className="h-14 w-14 bg-blue-100 rounded-full flex flex-shrink-0 justify-center items-center text-blue-500 text-2xl font-mono">
                i
              </div>
              <div className="block pl-2 font-semibold text-xl self-start text-gray-700">
                <h2 className="leading-relaxed">Realizar Transacción</h2>
                <p className="text-sm text-gray-500 font-normal leading-relaxed">
                  Complete los campos a continuación para realizar una
                  transacción.
                </p>
              </div>
            </div>
            <div className="divide-y divide-gray-200">
              <div className="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                <div className="relative">
                  <label htmlFor="ownerCards">Tarjeta del Remitente:</label>
                  <select
                    name="ownerCards"
                    id="ownerCards"
                    className="form-input py-2 pl-10 pr-4 block w-full rounded-md transition duration-150 ease-in-out sm:text-sm sm:leading-5 focus:outline-none border"
                    value={transactionData.senderCard} // Establece el valor seleccionado en base al estado
                    onChange={(e) => handleSenderInfo(e)} // Llama a la función de manejo de cambio
                  >
                    {senderCardList.map((card, index) => (
                      <option key={index} value={card.cardNumber}>
                        {card.cardNumber}
                      </option>
                    ))}
                  </select>
                  <span>Monto disponible: ${amount}</span>
                  <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none"></div>
                </div>
                <div className="relative">
                  <label htmlFor="ownerCards">Tarjeta del Receptor:</label>
                  <input
                    id="cardReceiver"
                    type="text"
                    className="form-input py-2 pl-10 pr-4 block w-full rounded-md transition duration-150 ease-in-out sm:text-sm sm:leading-5 focus:outline-none border"
                    value={transactionData.receiverCard}
                    onChange={(e) => handleNumberReceiverCard(e)}
                    pattern="[0-9]*"
                  />
                </div>
                <div className="relative">
                  <label htmlFor="ownerCards">Monto:</label>
                  <input
                    id="amount"
                    type="text"
                    placeholder="$"
                    className="form-input py-2 pl-10 pr-4 block w-full rounded-md transition duration-150 ease-in-out sm:text-sm sm:leading-5 focus:outline-none border"
                    value={transactionData.amount}
                    onChange={(e) => handleAmount(e)}
                    pattern="[0-9]*"
                  />
                </div>
              </div>
              <div className="pt-4 flex items-center space-x-4">
                <button
                  onClick={makeTransaction}
                  className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-400 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                >
                  Realizar Transacción
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
