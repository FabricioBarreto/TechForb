import jwt from "jsonwebtoken";

export function isTokenExpired(token) {
  try {
    const decodedToken = jwt.decode(token);
    if (decodedToken.exp < Date.now() / 1000) {
      return true; // El token ha expirado
    }
    return false; // El token es válido
  } catch (error) {
    return true; // Error al verificar el token
  }
}
