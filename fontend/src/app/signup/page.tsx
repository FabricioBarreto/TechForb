/* eslint-disable @next/next/no-img-element */
"use client";
import { useState } from "react";
import Link from "next/link";
import styles from "./signup.module.css";
import axios from "axios";
import { toast } from "react-hot-toast";
import { useRouter } from "next/navigation";

export default function Signup() {
  const router = useRouter();

  const [user, setUser] = useState({
    fullname: "",
    email: "",
    typeDocument: "0",
    numberDocument: "",
    password: "",
  });

  const handleTypeDocumentChange = (e) => {
    setUser({ ...user, typeDocument: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/signup",
        user
      );
      // Obtener el token del header
      const token = response.data.token;

      // Guardar token en localStorage
      localStorage.setItem("token", token);

      // Redirigir a home
      router.push("/");
    } catch (error) {
      // Mostrar error
      toast.error(error.message);
    }
  };

  return (
    <div className="flex">
      {/* Image Section */}
      <div className={styles.leftSide}>
        <p className={styles.brand}>
          Bienvenido a <span className={styles.brandSpan}>Unicommerce</span>
        </p>
      </div>
      {/* Login Form Section */}
      <div className={styles.rigthSide}>
        <div>
          <p className="text-2xl font-bold mt-1 ml-8">Logo</p>
        </div>
        <div className="flex justify-center">
          {/* Login Form */}
          <form
            className={styles.form}
            onSubmit={(event) => {
              event.preventDefault();
            }}
          >
            <label htmlFor="fullname">Nombre completo:</label>
            <input
              required
              type="text"
              className={styles.inputs}
              aria-label="Filter projects"
              name="fullname"
              id="fullname"
              value={user.fullname}
              onChange={(e) => setUser({ ...user, fullname: e.target.value })}
            />
            <label htmlFor="email">Correo:</label>
            <input
              required
              type="text"
              className={styles.inputs}
              aria-label="Filter projects"
              name="email"
              id="email"
              value={user.email}
              onChange={(e) => setUser({ ...user, email: e.target.value })}
            />

            <label htmlFor="typeDocument">Tipo de documento</label>
            <select
              id="typeDocument"
              required
              className={styles.inputs}
              name="typeDocument"
              value={user.typeDocument} // Establece el valor seleccionado en base al estado
              onChange={handleTypeDocumentChange} // Llama a la función de manejo de cambio
            >
              <option value="0" className="text-black bg-transparent">
                DNI
              </option>
              <option value="1" className="text-black bg-transparent">
                PASAPORTE
              </option>
            </select>

            <label htmlFor="numberDocument">Numero documento</label>
            <input
              required
              type="text"
              className={styles.inputs}
              aria-label="Filter projects"
              name="numberDocument"
              id="numberDocument"
              value={user.numberDocument}
              onChange={(e) =>
                setUser({ ...user, numberDocument: e.target.value })
              }
            />
            <label htmlFor="password">Clave</label>
            <input
              required
              type="password"
              className={styles.inputs}
              name="password"
              id="password"
              value={user.password}
              onChange={(e) => setUser({ ...user, password: e.target.value })}
            />
            <button className={styles.button} onClick={handleSubmit}>
              Crear
            </button>
          </form>
        </div>
        <div className="items-center text-center p-5">
          <p>
            ¿Ya tenes tu cuenta? <Link href="/login">Ingresar</Link>
          </p>
        </div>
      </div>
    </div>
  );
}
