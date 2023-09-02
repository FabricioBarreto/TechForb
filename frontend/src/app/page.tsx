"use client";
import Dashboard from "./components/Dashboard/Dashboard";
import useAuthHeader from "@/app/hooks/useAuthHeader";
import useAuth from "./hooks/useAuth";

export default function Home() {
  useAuthHeader();
  useAuth();

  // const hidenSidebar = (e) => {
  //   if (e.target.id == "btnSidebar") {
  //     e.target.classList.add("sideMenu_toggleSidebar__WFXU8");
  //   } else {
  //     if (document.getElementById("btnSidebar").classList.contains("fsd"))
  //       document
  //         .getElementById("sidebar")
  //         .classList.remove("sideMenu_toggleSidebar__WFXU8");
  //     document
  //       .getElementById("sidebar")
  //       .classList.remove("sideMenu_toggleSidebar__WFXU8");
  //   }
  // };

  return (
    <main
      className="flex min-h-screen flex-col items-center justify-between p-24"
      // onClick={hidenSidebar}
    >
      <Dashboard />
    </main>
  );
}
