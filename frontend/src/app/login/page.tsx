"use client";
import Link from "next/link";
import styles from "./login.module.css";
import axios from "axios";
import { toast } from "react-hot-toast";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Login() {
  const router = useRouter();

  const [credentials, setCredentials] = useState({
    typeDocument: "0",
    numberDocument: "",
    password: "",
  });

  const handleTypeDocumentChange = (e:any) => {
    const selectedType = e.target.value;
    setCredentials({ ...credentials, typeDocument: selectedType });
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/login",
        credentials
      );

      // Obtener el token del header
      const token = response.data.token;

      // Guardar token en localStorage
      localStorage.setItem("token", token);

      // Redirigir a home
      router.push("/");
    } catch (error: any) {
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
          <form className={styles.form}>
            <label htmlFor="typeDocument">Tipo de documento:</label>
            <select
              id="typeDocument"
              required
              className={styles.inputs}
              name="typeDocument"
              value={credentials.typeDocument} // Establece el valor seleccionado en base al estado
              onChange={handleTypeDocumentChange} // Llama a la función de manejo de cambio
            >
              <option value="0" className="text-black bg-transparent">
                DNI
              </option>
              <option value="1" className="text-black bg-transparent">
                PASAPORTE
              </option>
            </select>

            <label htmlFor="numberDocument">Numero documento:</label>
            <input
              required={true}
              type="text"
              className={styles.inputs}
              aria-label="Filter projects"
              name="numberDocument"
              id="numberDocument"
              value={credentials.numberDocument}
              onChange={(e) =>
                setCredentials({
                  ...credentials,
                  numberDocument: e.target.value,
                })
              }
            />

            <label htmlFor="password">Clave:</label>
            <input
              required={true}
              type="password"
              className={styles.inputs}
              name="password"
              id="password"
              value={credentials.password}
              onChange={(e) =>
                setCredentials({ ...credentials, password: e.target.value })
              }
            />

            <button className={styles.button} onClick={handleSubmit}>
              Ingresar
            </button>
          </form>
        </div>
        <div className="items-center text-center p-5">
          <p>
            ¿No tiene cuenta? <Link href="/signup">Registrate</Link>
          </p>
        </div>
      </div>
    </div>
  );
}
