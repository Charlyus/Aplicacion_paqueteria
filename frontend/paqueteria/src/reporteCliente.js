import React, { Component } from 'react';
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';


import { Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';

const url="http://localhost:8080/backend/ReporteCliente";

class App extends Component {
    state={
      data:[],
      modalInsertar: false,
      modalEliminar: false,
      actual:0,
      saliente:0,
      form:{
        nit: '',
        nombre: '',
        cantidad: '',
        costo: '',
        intreso: '',
        ganancia: '',
        tipoModal: ''
      }
    }
    
    peticionGet=()=>{
    axios.get(url).then(response=>{
      this.setState({data: response.data});
      this.actualizarVariables();
    }).catch(error=>{
      console.log(error.message);
    })
    }
    
    peticionPost=async()=>{
      delete this.state.form.id;
     await axios.post(url,this.state.form).then(response=>{
        this.modalInsertar();
        this.peticionGet();
      }).catch(error=>{
        console.log(error.message);
      })
    }
    
    peticionPut=()=>{
      axios.put(url+this.state.form.id, this.state.form).then(response=>{
        this.modalInsertar();
        this.peticionGet();
      })
    }
    
    peticionDelete=()=>{
      axios.delete(url+"/"+this.state.form.id).then(response=>{
        this.setState({modalEliminar: false});
        this.peticionGet();
      })
      .catch(error => {
        console.error('Error en la solicitud POST:', error);
    });
    }
    
    modalInsertar=()=>{
      this.setState({modalInsertar: !this.state.modalInsertar});
    }
    
    seleccionarEmpresa=(empresa)=>{
      this.setState({
        tipoModal: 'actualizar',
        form: {
          id: empresa.id,
          nombre: empresa.nombre,
          activo: empresa.activo,
        }
      })
    }
    
    handleChange=async e=>{
    e.persist();
    await this.setState({
      form:{
        ...this.state.form,
        [e.target.name]: e.target.value
      }
    });
    console.log(this.state.form);
    }
    
      componentDidMount() {
        this.peticionGet();
        
      }
      actualizarVariables = () => {
        let actual = 0;
        let saliente = 0;
      
        this.state.data.forEach(empresa => {
          if (empresa.enDestino) {
            saliente++;
          } else {
            actual++;
          }
        });
      
        this.setState({ actual, saliente });
      }
      
    
      render(){
        const {form}=this.state;
      return (
        <div className="App">
        <br /><br /><br />
            <br /><br />
        <table className="table ">
          <thead>
          <tr>
              <th>Nit</th>
              <th>Nombre</th>
              <th>Cantidad de paquetes</th>
              <th>costos totales</th>
              <th>ingresos totales</th>
              <th>ganancias totales</th>
            </tr>
          </thead>
          <tbody>
            {this.state.data.map(empresa=>{
              
              return(
                <tr>
              <td>{empresa.nit}</td>
              <td>{empresa.nombre}</td>
              <td>{empresa.cantidad}</td>
              <td>{"Q"+empresa.costo}</td>
              <td>{"Q"+empresa.intreso}</td>
              <td>{"Q"+empresa.ganancia}</td>
              </tr>
              )
            })}
          </tbody>
        </table>
        
        <Modal isOpen={this.state.modalInsertar}>
                    <ModalHeader style={{display: 'block'}}>
                      <span style={{float: 'right'}} onClick={()=>this.modalInsertar()}>x</span>
                    </ModalHeader>
                    <ModalBody>
                      <div className="form-group">
                        <label htmlFor="id">ID</label>
                        <input className="form-control" type="text" name="id" id="id" readOnly onChange={this.handleChange} value={form?form.id: this.state.data.length+1}/>
                        <br />
                        <label htmlFor="nombre">Nombre</label>
                        <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.handleChange} value={form?form.nombre: ''}/>
                        <br />
                        <label htmlFor="nombre">Contraseña</label>
                        <input className="form-control" type="text" name="contraseña" id="contraseña" onChange={this.handleChange} value={form?form.contraseña: ''}/>
                        <br />
                        <label htmlFor="activo">activo</label>
                        <input className="form-control" type="text" name="activo" id="activo" readOnly value={form ? (form.activo ? 'No' : 'Si') :''}/>
                        <br />
                      </div>
                    </ModalBody>
    
                    <ModalFooter>
                      {this.state.tipoModal=='insertar'?
                        <button className="btn btn-success" onClick={()=>this.peticionPost()}>
                        Insertar
                      </button>: <button className="btn btn-primary" onClick={()=>this.peticionPut()}>
                        Actualizar
                      </button>
      }
                        <button className="btn btn-danger" onClick={()=>this.modalInsertar()}>Cancelar</button>
                    </ModalFooter>
              </Modal>
    
    
              <Modal isOpen={this.state.modalEliminar}>
                <ModalBody>
                   Estás seguro que deseas eliminar al operador {form && form.nombre}
                </ModalBody>
                <ModalFooter>
                  <button className="btn btn-danger" onClick={()=>this.peticionDelete()}>Sí</button>
                  <button className="btn btn-secundary" onClick={()=>this.setState({modalEliminar: false})}>No</button>
                </ModalFooter>
              </Modal>
      </div>
    
    
    
      );
    }
    }

export default App;