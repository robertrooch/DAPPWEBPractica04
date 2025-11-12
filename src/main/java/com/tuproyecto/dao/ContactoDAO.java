package com.tuproyecto.dao;
import com.tuproyecto.modelo.Contacto;
import java.util.List;
import java.util.Optional;

public interface ContactoDAO {
    void crear(Contacto contacto) throws Exception;
    Optional<Contacto> obtenerPorClave(String clave) throws Exception;
    List<Contacto> obtenerTodos() throws Exception;
    void actualizar(Contacto contacto) throws Exception;
    void eliminar(String clave) throws Exception;
    boolean claveExiste(String clave) throws Exception;
}
