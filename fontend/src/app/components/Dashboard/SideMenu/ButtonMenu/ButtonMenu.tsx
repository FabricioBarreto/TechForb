import Link from "next/link";
import {
  ArrowLeftOnRectangleIcon,
  ArrowsRightLeftIcon,
  CreditCardIcon,
  HomeIcon,
} from "@heroicons/react/24/solid";
import styles from "@/app/components/Dashboard/SideMenu/ButtonMenu/ButtonMenu.module.css";
import { isTokenExpired } from "@/app/helpers/IsTokenExpired";
import { useRouter } from "next/navigation";

export default function ButtonMenu({ nameItem, urlItem }: any) {
  const router = useRouter();

  let token: any;

  const logout = () => {
    if (!token || isTokenExpired(token)) {
      localStorage.delete("token");
      router.push("/login"); // Redirige al inicio de sesión si no hay token o ha expirado
    }
  };

  return (
    <div>
      {nameItem === "home" && (
        <Link className={styles.sidebarA} href={urlItem} onClick={logout}>
          <>
            <HomeIcon className="w-4 h-4 inline mr-2 rounded-md" />
            {nameItem}
          </>
        </Link>
      )}
      {nameItem === "transaction" && (
        <Link className={styles.sidebarA} href={urlItem} onClick={logout}>
          <>
            <ArrowsRightLeftIcon className="w-4 h-4 inline mr-2 rounded-md" />
            {nameItem}
          </>
        </Link>
      )}
      {nameItem === "cards" && (
        <Link className={styles.sidebarA} href={urlItem} onClick={logout}>
          <>
            <CreditCardIcon className="w-6 h-6 inline mr-2 rounded-md" />
            {nameItem}
          </>
        </Link>
      )}
      {nameItem === "logout" && (
        <Link className={styles.sidebarA} href={"/login"} onClick={logout}>
          <>
            <ArrowLeftOnRectangleIcon className="w-6 h-6 inline mr-2 rounded-md" />
            {nameItem}
          </>
        </Link>
      )}
    </div>
  );
}
