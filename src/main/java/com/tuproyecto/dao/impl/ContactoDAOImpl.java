package com.tuproyecto.dao.impl;

import com.tuproyecto.dao.ContactoDAO;
import com.tuproyecto.modelo.Contacto;
import com.tuproyecto.util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactoDAOImpl implements ContactoDAO {
    private Connection conn;

    public ContactoDAOImpl() throws SQLException {
        this.conn = ConexionDB.getConexion();
    }

    @Override
    public void crear(Contacto contacto) throws Exception {
        String sql = "INSERT INTO contactos (clave, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contacto.getClave());
            ps.setString(2, contacto.getNombre());
            ps.setString(3, contacto.getTelefono());
            ps.setString(4, contacto.getDireccion());
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Contacto> obtenerPorClave(String clave) throws Exception {
        String sql = "SELECT * FROM contactos WHERE clave = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Contacto contacto = new Contacto(
                        rs.getString("clave"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion")
                    );
                    return Optional.of(contacto);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Contacto> obtenerTodos() throws Exception {
        List<Contacto> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactos ORDER BY clave";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                contactos.add(new Contacto(
                    rs.getString("clave"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                ));
            }
        }
        return contactos;
    }


    @Override
    public void actualizar(Contacto contacto) throws Exception {
        String sql = "UPDATE contactos SET nombre = ?, telefono = ?, direccion = ? WHERE clave = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getTelefono());
            ps.setString(3, contacto.getDireccion());
            ps.setString(4, contacto.getClave());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String clave) throws Exception {
        String sql = "DELETE FROM contactos WHERE clave = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clave);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean claveExiste(String clave) throws Exception {
        String sql = "SELECT 1 FROM contactos WHERE clave = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, clave);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
