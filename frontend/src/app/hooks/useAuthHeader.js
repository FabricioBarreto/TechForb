"use client";
import axios from "axios";

export default function useAuthHeader() {
  // Función para obtener el token de autenticación
  const getAccessToken = () => {
    return localStorage.getItem("token");
  };

  // Verificar la autenticación antes de navegar
  const isAuthenticated = () => {
    return !!localStorage.getItem("token");
  };

  // Configurar interceptor de solicitud de Axios
  axios.interceptors.request.use((config) => {
    const token = getAccessToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
}
