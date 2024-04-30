import React from 'react';
import ReactDOM from 'react-dom';
import Operadores from './Operadores'; // Importa el componente Operadores
import Recepcionistas from './Recepcionistas'; // Importa el componente Recepcionista
import SeguimientoPaquete from './SeguimientoPaquete'; // Importa el componente SeguimientoPaquete
import Paquetes from './Paquetes'; // Importa el componente Paquete

function Principal() {
  const handleClick = (componente) => {
    ReactDOM.render(
      <React.StrictMode>
        {componente}
      </React.StrictMode>,
      document.getElementById('root')
    );
  };

  return (
    <div>
      <h1>Principal</h1>
      <button onClick={() => handleClick(<Operadores />)}>Mostrar Operadores</button>
      <button onClick={() => handleClick(<Recepcionistas />)}>Mostrar Recepcionista</button>
      <button onClick={() => handleClick(<SeguimientoPaquete />)}>Mostrar Seguimiento de Paquete</button>
      <button onClick={() => handleClick(<Paquetes />)}>Mostrar Paquetes</button>
    </div>
  );
}

export default Principal;


