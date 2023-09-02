import { useEffect, useState } from "react";
import style from "./Card.module.css";
import jwt from "jsonwebtoken";
import axios from "axios";
import { format } from "date-fns";

export default function Card() {
  const [card, setCard] = useState({
    amount: 0.0,
    dateOfExp: "",
    number: "",
    ownerName: "",
  });

  const token = localStorage.getItem("token");

  const handleUpload = () => {
    if (token) {
      const decodedToken = jwt.decode(token);
      const userEmail = decodedToken ? decodedToken.sub : null;
      axios.get(`http://localhost:8080/api/card/${userEmail}`).then((res) => {
        setCard(res.data);
      });
    }
  };

  useEffect(() => {
    if (token) {
      handleUpload();
    }
  }, []);

  const formattedNumber = "**** **** **** " + card.number.slice(-4);

  // Convertir a formato MM/DD
  const shortDate = card.dateOfExp
    ? format(new Date(card.dateOfExp), "MM/yy")
    : "";

  return (
    <div className={style.card}>
      <div className={style.amount}>
        <p className={style.amountP}>
          Saldo:
          <br />
          <span className="font-bold">${card.amount}</span>
        </p>
      </div>
      <div className={style.cardLogo}>Logo</div>
      <p className={style.cardNumber}>{formattedNumber}</p>
      <p className={style.cardDataName}>
        Titular: <br />
        <span className="font-bold">{card.ownerName}</span>
      </p>
      <p className={style.cardExpiration}>
        Exp: <br />
        <span className="font-bold">{shortDate}</span>
      </p>
    </div>
  );
}
