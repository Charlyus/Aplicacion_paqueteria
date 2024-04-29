import React, { useState } from 'react';

function AdminLogin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Realizar una solicitud GET a la API para obtener los datos del administrador
    const url = `http://localhost:8080/backend/Administrador/${username}`;
    
    try {
        
      const response = await fetch(url);
      
      const data = await response.json();
        
      // Verificar si los datos obtenidos de la API coinciden con los datos ingresados por el usuario
      if (data.nombre === username && data.contraseña === password) {
        // Los datos son correctos, el usuario está autenticado
        console.log('Login exitoso');
        // Aquí podrías redirigir al usuario a otra página o realizar alguna acción adicional
      } else {
        // Los datos son incorrectos, mostrar un mensaje de error
        setError('Nombre de usuario o contraseña incorrectos');
      }
    } catch (error) {
      // Error al realizar la solicitud a la API
      console.error('Error al obtener los datos del administrador:', error);
      setError('Error al obtener los datos del administrador');
    }
  };

  return (
    <div>
      <h2>Administrador Login</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
      {error && <p>{error}</p>} {/* Mostrar el mensaje de error si existe */}
    </div>
  );
}

export default AdminLogin;