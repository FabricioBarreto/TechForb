"use client";
import axios from "axios";
import { useEffect, useState } from "react";
import useAuthHeader from "@/app/hooks/useAuthHeader";
import ButtonMenu from "@/app/components/ButtonMenu/ButtonMenu";
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
    <div className={style.sidebar + " " + style.toggleSidebar} id="sidebar">
      <div className="flex flex-col justify-between">
        <h1 className="text-2xl font-semibold m-auto mt-3 mb-10">LOGO</h1>
        <ul className="mt-4">
          {menu.map((item: any) => (
            <li key={item.name} className={"mb-2"}>
              <div>
                <ButtonMenu nameItem={item.name} urlItem={item.url} />
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
