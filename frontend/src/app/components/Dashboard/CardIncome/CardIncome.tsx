import { useEffect, useState } from "react";
import style from "./Card.module.css";
import jwt from "jsonwebtoken";
import axios from "axios";

export default function CardIncome() {
  const [card, setCard] = useState({
    amount: 0.0,
    percentage: "",
    line: "",
  });

  const token = localStorage.getItem("token");

  const handleUpload = () => {
    if (token) {
      const decodedToken = jwt.decode(token);
      const userEmail = decodedToken ? decodedToken.sub : null;
      axios
        .get(`http://localhost:8080/api/card/incomes${userEmail}`)
        .then((res) => {
          setCard(res.data);
        });
    }
  };

  useEffect(() => {
    if (token) {
      handleUpload();
    }
  }, []);

  return (
    <div className={style.card}>
      <h1>Logo</h1>
      <p>
        Ingresos:
        <br />
        <span>{card.amount}</span>
      </p>
      <p>{card.percentage}</p>
      <div>Line</div>
    </div>
  );
}
