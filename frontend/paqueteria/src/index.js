import React from 'react';
import ReactDOM from 'react-dom';
import AdminLogin from './AdminLogin'; // Importa el componente AdminLogin
import Operadores from './Operadores'
import Recepcionistas from './Recepcionistas'

ReactDOM.render(
  <React.StrictMode>
    <Recepcionistas /> {/* Incluye el componente AdminLogin */}
  </React.StrictMode>,
  document.getElementById('root')
);
