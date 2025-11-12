package com.tuproyecto.controlador;

import com.tuproyecto.dao.ContactoDAO;
import com.tuproyecto.dao.impl.ContactoDAOImpl;
import com.tuproyecto.modelo.Contacto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.util.Optional;   

@WebServlet("/contactos")
public class ContactoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ContactoDAO contactoDAO;

    @Override
    public void init() throws ServletException {
        try {
            this.contactoDAO = new ContactoDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("No se pudo inicializar el DAO de contactos", e);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "consultar";
        }

        try {
            switch (action) {
                case "nuevo":
                    mostrarFormulario(request, response, null);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarContacto(request, response);
                    break;
                case "consultar":
                default:
                    listarContactos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error ejecutando acción GET", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/contactos");
            return;
        }

        try {
            switch (action) {
                case "guardar":
                    guardarContacto(request, response);
                    break;
                case "actualizar":
                    actualizarContacto(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/contactos");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error ejecutando acción POST", e);
        }
    }

    private void listarContactos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Contacto> listaContactos = contactoDAO.obtenerTodos();
        request.setAttribute("listaContactos", listaContactos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("consultar.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Contacto contacto) throws ServletException, IOException {
        request.setAttribute("contacto", contacto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario-contacto.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clave = request.getParameter("clave");
        Optional<Contacto> contactoExistente = contactoDAO.obtenerPorClave(clave);
        if (contactoExistente.isPresent()) {
            mostrarFormulario(request, response, contactoExistente.get());
        } else {
            response.sendRedirect(request.getContextPath() + "/contactos");
        }
    }

    private void guardarContacto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clave = request.getParameter("clave");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Contacto nuevoContacto = new Contacto(clave, nombre, telefono, direccion);
        contactoDAO.crear(nuevoContacto);

        response.sendRedirect(request.getContextPath() + "/contactos");
    }

    private void actualizarContacto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clave = request.getParameter("clave");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Contacto contactoActualizado = new Contacto(clave, nombre, telefono, direccion);
        contactoDAO.actualizar(contactoActualizado);

        response.sendRedirect(request.getContextPath() + "/contactos");
    }

    private void eliminarContacto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clave = request.getParameter("clave");
        contactoDAO.eliminar(clave);
        response.sendRedirect(request.getContextPath() + "/contactos");
    }
}