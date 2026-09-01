import { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {

  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    address: ""
  });

  const [image, setImage] = useState(null);

  const [message, setMessage] = useState("");

  const handleChange = (e) => {

    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleImageChange = (e) => {

    setImage(e.target.files[0]);
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    const formData = new FormData();

    formData.append("name", form.name);
    formData.append("email", form.email);
    formData.append("phone", form.phone);
    formData.append("address", form.address);
    formData.append("image", image);

    try {

      const response = await axios.post(
        "http://3.111.36.109/api/users",
        formData
      );

      console.log(response.data);

      setMessage("User created successfully!");

      setForm({
        name: "",
        email: "",
        phone: "",
        address: ""
      });

      setImage(null);

    } catch (error) {

      console.error(error);

      setMessage("Failed to create user.");
    }
  };

  return (
    <div className="container">

      <h1>User Information</h1>

      <form onSubmit={handleSubmit}>

        <div className="form-group">
          <label>Name</label>

          <input
            type="text"
            name="name"
            value={form.name}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Email</label>

          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Phone</label>

          <input
            type="text"
            name="phone"
            value={form.phone}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Address</label>

          <textarea
            name="address"
            value={form.address}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Profile Image</label>

          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
          />
        </div>

        <button type="submit">
          Create User
        </button>

      </form>

      {message && (
        <p className="message">
          {message}
        </p>
      )}

    </div>
  );
}

export default App;