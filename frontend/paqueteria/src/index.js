import React from 'react';
import ReactDOM from 'react-dom';
import AdminLogin from './AdminLogin'; // Importa el componente AdminLogin
import Operadores from './Operadores';
import Recepcionistas from './Recepcionistas';
import SeguimientoPaquete from './SeguimientoPaquete';

ReactDOM.render(
  <React.StrictMode>
    <SeguimientoPaquete /> {/* Incluye el componente AdminLogin */}
  </React.StrictMode>,
  document.getElementById('root')
);
