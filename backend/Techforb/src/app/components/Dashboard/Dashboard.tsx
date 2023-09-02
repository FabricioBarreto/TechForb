"use client";
import style from "./Dashboard.module.css";
import Card from "./Card/Card";
import CardIncome from "./CardIncome/CardIncome";
import CardEgreso from "./CardEgreso/CardEgreso";
import Header from "./Header/Header";
import SideMenu from "./SideMenu/SideMenu";

export default function Dashboard() {
  return (
    <div className={style.dashboard + " " + "flex min-w-450"}>
      <Header />
      <SideMenu />
      <Card />
      <CardIncome />
      <CardEgreso />
    </div>
  );
}
