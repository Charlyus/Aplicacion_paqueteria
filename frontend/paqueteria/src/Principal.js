import React from 'react';
import ReactDOM from 'react-dom';
import Operadores from './Operadores'; // Importa el componente Operadores
import Recepcionistas from './Recepcionistas'; // Importa el componente Recepcionista
import SeguimientoPaquete from './SeguimientoPaquete'; // Importa el componente SeguimientoPaquete
import Paquetes from './Paquetes'; // Importa el componente Paquete
import PaquetesDestino from './PaquetesDestino';
import Rutas from './Rutas';
import PuntoControl from './PuntoControl';
import ReporteClientes from './reporteCliente';
import ReporteRutas from './ReporteRuta';

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
      <button onClick={() => handleClick(<Operadores />)}>Operadores</button>
      <button onClick={() => handleClick(<Recepcionistas />)}>Recepcionista</button>
      <button onClick={() => handleClick(<SeguimientoPaquete />)}>localizar paquete</button>
      <button onClick={() => handleClick(<Paquetes />)}>Reporte Rutas</button>
      <button onClick={() => handleClick(<PaquetesDestino />)}>Paquetes ya en destino</button>
      <button onClick={() => handleClick(<PuntoControl />)}>Puntos De Control</button>
      <button onClick={() => handleClick(<Rutas />)}>Rutas</button>
      <button onClick={() => handleClick(<ReporteClientes />)}>Reporte Clientes</button>
      <button onClick={() => handleClick(<ReporteRutas />)}> Rutas mas populares</button>
    </div>
  );
}

export default Principal;


