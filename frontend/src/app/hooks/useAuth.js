import { useRouter } from "next/navigation";
import { isTokenExpired } from "../helpers/IsTokenExpired";
import useAuthHeader from "./useAuthHeader";

export default function useAuth() {
  useAuthHeader();
  const router = useRouter();

  if (
    !localStorage.getItem("token") ||
    isTokenExpired(localStorage.getItem("token"))
  ) {
    localStorage.removeItem("token");
    router.replace("/login");
    return null;
  }
}
