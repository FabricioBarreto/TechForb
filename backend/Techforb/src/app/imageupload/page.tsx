"use client";
import axios from "axios";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { toast } from "react-hot-toast";
import jwt from "jsonwebtoken";
import { isTokenExpired } from "../helpers/IsTokenExpired";

function ImageUpload() {
  const token = localStorage.getItem("token");
  const router = useRouter();

  const [selectedImage, setSelectedImage] = useState("");

  const handleImageChange = (e: any) => {
    const file = e.target.files[0];
    setSelectedImage(file);
  };

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      router.push("/login"); // Redirige al inicio de sesión si no hay token o ha expirado
    }
  }, []);

  const handleUpload = (e: any) => {
    e.preventDefault();
    try {
      if (token) {
        const decodedToken = jwt.decode(token);
        const userEmail = decodedToken ? decodedToken.sub : null;

        if (selectedImage) {
          const formData = new FormData();
          formData.append("file", selectedImage);

          axios
            .post(`http://localhost:8080/api/upload/${userEmail}`, formData, {
              headers: {
                "Content-Type": "multipart/form-data",
                Authorization: `Bearer ${token}`,
              },
            })
            .then((res) => {
              router.push("/");
            });
        }
      }
    } catch (error: any) {
      toast.error(error.message);
    }
  };

  return (
    <div>
      <input type="file" accept="image/*" onChange={handleImageChange} />
      <button onClick={handleUpload}>Subir Imagen</button>
    </div>
  );
}

export default ImageUpload;
