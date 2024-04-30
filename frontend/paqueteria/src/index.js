import React from 'react';
import ReactDOM from 'react-dom';
import AdminLogin from './AdminLogin'; 
import Operadores from './Operadores';
import Recepcionistas from './Recepcionistas';
import SeguimientoPaquete from './SeguimientoPaquete';
import Paquetes from './Paquetes';
import Principal from './Principal';

ReactDOM.render(
  <React.StrictMode>
    <Principal /> {/* Incluye el componente AdminLogin */}
  </React.StrictMode>,
  document.getElementById('root')
);
