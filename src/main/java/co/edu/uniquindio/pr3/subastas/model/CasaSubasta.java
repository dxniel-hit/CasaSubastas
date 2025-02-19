package co.edu.uniquindio.pr3.subastas.model;

import co.edu.uniquindio.pr3.subastas.exceptions.AnuncianteException;
import co.edu.uniquindio.pr3.subastas.exceptions.CompradorException;
import co.edu.uniquindio.pr3.subastas.exceptions.UsuarioException;
import co.edu.uniquindio.pr3.subastas.model.Interfaces.ISubasta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CasaSubasta implements ISubasta {

    private String nombre;
    private List<Comprador> listaCompradores= new ArrayList<>();
    private List<Anunciante> listaAnunciantes= new ArrayList<>();
    private List<Usuario> listaUsuarios= new ArrayList<>();
    private  List<Producto> listaProductos= new ArrayList<>();
    private List<Anuncio> listaAnuncios= new ArrayList<>();

    //Constructores
    public CasaSubasta(String nombre) {
        this.nombre = nombre;
        this.listaCompradores= new ArrayList<Comprador>();
        this.listaAnunciantes=new ArrayList<Anunciante>();
        this.listaUsuarios= new ArrayList<Usuario>();
        this.listaProductos= new ArrayList<Producto>();
        this.listaAnuncios= new ArrayList<Anuncio>();
    }

    public CasaSubasta() {
        this.listaCompradores= new ArrayList<Comprador>();
        this.listaAnunciantes=new ArrayList<Anunciante>();
        this.listaUsuarios= new ArrayList<Usuario>();
        this.listaProductos= new ArrayList<Producto>();
        this.listaAnuncios= new ArrayList<Anuncio>();
    }

    //Metodos getters and setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Comprador> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(List<Comprador> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    public List<Anunciante> getListaAnunciantes() {
        return listaAnunciantes;
    }

    public void setListaAnunciantes(List<Anunciante> listaAnunciantes) {
        this.listaAnunciantes = listaAnunciantes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Anuncio> getListaAnuncios() {
        return listaAnuncios;
    }

    public void setListaAnuncios(List<Anuncio> listaAnuncios) {
        this.listaAnuncios = listaAnuncios;
    }

    /**
     *
     * @param usuario
     * @return true, si la lista "UsuariosIdenticos" NO está vacia, esto significa que encontro un usuario con el mismo nombreDeusuario y contraseña y lo añadio a la lista
     * @return false, si la lista "UsuariosIdenticos" permance vacia, ya que no encontraria ningun usuario con el mismo nombreDeUsuario y contraseña
     */
    @Override
    public boolean verificarUsuario(String usuario) {
        boolean encontrado= false;
        List<Usuario> usuariosIdenticos = this.listaUsuarios.stream()
                .filter(u-> u.getNombreUsuario().equals(usuario))
                .collect(Collectors.toList());
                ;
        if(!usuariosIdenticos.isEmpty()) {
            encontrado = true;
        }

        return encontrado;
    }
    //----------------------CRUD DEL COMPRADOR--------------------------------------------------

    @Override
    public Comprador obtenerComprador(String usuario, String contrasenia) {
        return (Comprador) listaCompradores.stream().filter(c-> c.getNombreUsuario().equals(usuario) && c.getContrasenia().equals(contrasenia));
    }

    /**
     *
     * @param newComprador
     * @return
     * @throws UsuarioException
     * @throws CompradorException
     */
    @Override
    public boolean crearComprador(Comprador newComprador) throws UsuarioException, CompradorException {
        boolean creado= false;
        if (verificarUsuario(newComprador.getNombreUsuario())){
            throw new CompradorException("Este Usuario ya se encuentra registrado");
        }else {
            creado=true;
            listaUsuarios.add(newComprador);
            listaCompradores.add(newComprador);
        }
        return creado;
    }

    /**
     *
     * @param newComprador
     * @return
     * @throws UsuarioException
     * @throws CompradorException
     */
    @Override
    public boolean actualizarComprador(Comprador newComprador) throws UsuarioException, CompradorException {
        boolean actualizado= false;
        Comprador compradorAux= obtenerComprador(newComprador.getNombreUsuario(), newComprador.getContrasenia());
        if (compradorAux==null){
            throw new CompradorException("El usuario no ha sido encontrado");
        }else {
            actualizado=true;
            compradorAux.setNombre(newComprador.getNombre());
            compradorAux.setApellido(newComprador.getApellido());
            compradorAux.setEdad(newComprador.getEdad());
            compradorAux.setCorreo(newComprador.getCorreo());
            compradorAux.setContrasenia(newComprador.getContrasenia());
            compradorAux.setNombreUsuario(newComprador.getNombreUsuario());
        }
        return actualizado;
    }

    @Override
    public boolean eliminarComprador(Comprador compradorEliminar) throws UsuarioException,CompradorException{
        boolean eliminado=false;
        Comprador compradorAux= obtenerComprador(compradorEliminar.getNombreUsuario(),compradorEliminar.getContrasenia());
        if (compradorAux==null){
            throw new CompradorException("El usuario no ha sido encontrado");
        }else{
            eliminado=true;
            //Se elimina el usuario de las respectivas listas
            listaUsuarios.remove(compradorAux);
            listaCompradores.remove(compradorAux);
        }

        return eliminado;
    }

    //----------------------CRUD DEL ANUNCIANTE--------------------------------------------------
    @Override
    public Anunciante obtenerAnunciante(String usuario, String contrasenia) {
        return (Anunciante) listaAnunciantes.stream().filter(a->a.getNombreUsuario().equals(usuario) && a.getContrasenia().equals(contrasenia));
    }
    /**
     *
     * @param newAnunciante
     * @return
     * @throws UsuarioException
     * @throws AnuncianteException
     */
    @Override
    public boolean crearAnunciante(Anunciante newAnunciante) throws UsuarioException, AnuncianteException {
        boolean creado= false;
        if (verificarUsuario(newAnunciante.getNombreUsuario())){
            throw new AnuncianteException("Ya existe un usuario con este mismo nombre");
        }else {
            creado=true;
            listaUsuarios.add(newAnunciante);
            listaAnunciantes.add(newAnunciante);
        }
        return creado;
    }

    /**
     *
     * @param newAnunciante
     * @return
     * @throws UsuarioException
     * @throws AnuncianteException
     */
    @Override
    public boolean actualizarAnunciante(Anunciante newAnunciante)throws UsuarioException, AnuncianteException {
        boolean actualizado=true;
        Anunciante anuncianteAux= obtenerAnunciante(newAnunciante.getNombreUsuario(), newAnunciante.getContrasenia());
        if (anuncianteAux==null){
            throw new AnuncianteException("El usuario no ha sido registrado");
        }else{
            actualizado=true;
            anuncianteAux.setNombre(newAnunciante.getNombre());
            anuncianteAux.setApellido(newAnunciante.getApellido());
            anuncianteAux.setEdad(newAnunciante.getEdad());
            anuncianteAux.setCorreo(newAnunciante.getCorreo());
            anuncianteAux.setContrasenia(newAnunciante.getContrasenia());
            anuncianteAux.setNombreUsuario(newAnunciante.getNombreUsuario());
        }

        return actualizado;
    }

    /**
     *
     * @param anuncianteEliminar
     * @return
     * @throws UsuarioException
     * @throws AnuncianteException
     */
    @Override
    public boolean eliminarAnunciante(Anunciante anuncianteEliminar) throws UsuarioException, AnuncianteException{
        boolean eliminado=false;
        Anunciante anuncianteAux= obtenerAnunciante(anuncianteEliminar.getNombreUsuario(), anuncianteEliminar.getContrasenia());
        if (anuncianteAux==null){
            throw new AnuncianteException("El usuario no ha sido encontrado");
        }else{
            eliminado=true;
            listaAnunciantes.remove(anuncianteAux);
            listaUsuarios.remove(anuncianteAux);
        }
        return  eliminado;
    }




}
