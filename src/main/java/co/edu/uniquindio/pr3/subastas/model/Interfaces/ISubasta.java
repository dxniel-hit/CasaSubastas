package co.edu.uniquindio.pr3.subastas.model.Interfaces;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Anunciante;
import co.edu.uniquindio.pr3.subastas.model.Comprador;
import co.edu.uniquindio.pr3.subastas.model.TipoUsuario;

public interface ISubasta {
    public boolean verificarUsuario(String usuario);//Metodo de usuarios
    //...................Metodos del comprador..............................

    public Comprador obtenerComprador(String usuario, String contrasenia);
    public boolean crearComprador(Comprador newComprador) throws UsuarioException, CompradorException;
    public boolean actualizarComprador(Comprador newComprador) throws UsuarioException, CompradorException;
    public boolean eliminarComprador(Comprador compradorEliminar) throws UsuarioException, CompradorException;
    //................Metodos del anunciante.................................

    public Anunciante obtenerAnunciante(String usuario, String contrasenia);
    public boolean crearAnunciante(Anunciante newAnunciante) throws UsuarioException, AnuncianteException;
    public boolean actualizarAnunciante(Anunciante newAnunciante) throws UsuarioException, AnuncianteException;
    public boolean eliminarAnunciante(Anunciante anuncianteEliminar) throws UsuarioException, AnuncianteException;

}
