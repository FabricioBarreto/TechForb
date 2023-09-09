"use client";
import axios from "axios";
import { useEffect, useState } from "react";
import useAuthHeader from "@/app/hooks/useAuthHeader";
import ButtonMenu from "@/app/components/Dashboard/SideMenu/ButtonMenu/ButtonMenu";
import style from "./sideMenu.module.css";

export default function SideMenu() {
  useAuthHeader();

  const [menu, setMenu] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/menu").then((res) => {
      setMenu(res.data);
    });
  }, []);

  return (
    <div className={style.sidebar} id="sidebar">
      <h1 className={style.brand}>LOGO</h1>
      <ul>
        {menu.map((item: any) => (
          <li key={item.name} className={style.sidebarLi}>
            <ButtonMenu nameItem={item.name} urlItem={item.url} />
          </li>
        ))}
      </ul>
    </div>
  );
}
