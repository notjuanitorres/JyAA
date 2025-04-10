package paquetes.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import paquetes.dao.ItemCasamientoDAOImpl;
import paquetes.model.ItemCasamiento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GenerarFormulario")
public class GenerarFormulario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GenerarFormulario() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los ítems desde la base de datos
        ItemCasamientoDAOImpl itemDAO = new ItemCasamientoDAOImpl();
        List<ItemCasamiento> items = itemDAO.obtenerTodos();       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Formulario de Presupuesto</title></head><body>");
        out.println("<h1>Presupuesto Estimativo de Boda</h1>");
        out.println("<form action='GenerarPresupuesto' method='post'>");

        out.println("<label for='nombre'>Nombre:</label><br>");
        out.println("<input type='text' id='nombre' name='nombre' required><br><br>");

        out.println("<label for='email'>Email:</label><br>");
        out.println("<input type='email' id='email' name='email' required><br><br>");

        out.println("<h3>Seleccioná los ítems:</h3>");

        for (ItemCasamiento item : items) {
            String nombre = item.getNombre();
            if ("invitados".equalsIgnoreCase(nombre)) {
                out.println("<label for='invitados'>" + nombre + ":</label>");
                out.println("<input type='number' id='invitados' name='invitados' min='1' required><br><br>");
            } else {
                out.println("<input type='checkbox' id='" + nombre + "' name='itemsSeleccionados' value='" + nombre + "'>");
                out.println("<label for='" + nombre + "'>" + nombre + " - $" + item.getPrecio() + "</label><br>");
            }
        }

        out.println("<br><input type='submit' value='Calcular Presupuesto'>");
        out.println("</form></body></html>");
    }
} 
