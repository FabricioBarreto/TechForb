"use client";
import useAuthHeader from "@/app/hooks/useAuthHeader";
import axios from "axios";
import Image from "next/image";
import { useEffect, useState } from "react";
import jwt from "jsonwebtoken";
import img from "@/../public/assets/img/perfil.png";
import { Bars3BottomLeftIcon } from "@heroicons/react/24/solid";
import style from "./Header.module.css";

export default function Header() {
  useAuthHeader();
  
  let token:any;


  const handleUpload = () => {
    if (token) {
      const decodedToken = jwt.decode(token);
      const userEmail = decodedToken ? decodedToken.sub : null;
      axios.get(`http://localhost:8080/api/user/${userEmail}`).then((res) => {
        setUser(res.data);
      });
    }
  };


  if (typeof window !== "undefined") {
    token = localStorage.getItem("token");
  }

  useEffect(() => {
    if (token) {
      handleUpload();
    }
  }, [token]);

  const [user, setUser] = useState({
    fullname: "",
    numberDocument: "",
    typeDocument: "0",
    profilePhoto: "",
  });

  const [card, setCard] = useState({
    owner: "",
    cardNumber: "",
    amount: "",
    dateOfExp: "",
  });

  return (
    <header className="bg-transparent p-6 flex row-auto justify-between items-center text-center absolute top-0 right-0 w-full min-w-450">
      <Bars3BottomLeftIcon
        className={
          style.btnSidebar +
          " " +
          "text-cyan-50 relative cursor-pointer min-w-500"
        }
        id="btnSidebar"
      ></Bars3BottomLeftIcon>
      <div className={style.searcher}>
        <input
          type="text"
          placeholder="Buscar"
          className={style.searcherInput}
        />
      </div>
      <div className="flex items-center">
        <button className="text-white mr-4">
          <i className="fas fa-bell"></i>{" "}
        </button>
        <div className="flex items-center">
          <Image
            src={user.profilePhoto || img}
            alt="Avatar"
            className="w-8 h-8 rounded-full mr-2"
          />
          <span className="text-white">{user.fullname}</span>
        </div>
      </div>
    </header>
  );
}
