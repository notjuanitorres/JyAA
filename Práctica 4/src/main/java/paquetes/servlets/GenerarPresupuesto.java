package paquetes.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import paquetes.model.ItemCasamiento;
import paquetes.model.Visitante;
import paquetes.dao.ItemCasamientoDAOImpl;
import java.io.IOException;
import java.util.List;
@WebServlet("/GenerarPresupuesto")
public class GenerarPresupuesto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;
    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory("miUP");
    }
    @Override
    public void destroy() {
        emf.close();
        super.destroy();
    }
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        int invitados = Integer.parseInt(request.getParameter("invitados"));

        ItemCasamientoDAOImpl itemDAO = new ItemCasamientoDAOImpl();
        List<ItemCasamiento> items = itemDAO.obtenerTodos();
        double total = 0.0;
        for (ItemCasamiento item : items) {
        	String[] seleccionados = request.getParameterValues("itemsSeleccionados");

        	if (item.getNombre().equalsIgnoreCase("invitados")) {
        	    total += item.getPrecio() * invitados;
        	} else if (seleccionados != null) {
        	    for (String nombreSeleccionado : seleccionados) {
        	        if (nombreSeleccionado.equalsIgnoreCase(item.getNombre())) {
        	            total += item.getPrecio();
        	            break;
        	        }
        	    }
        	}
        }

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Visitante visitante = new Visitante();
        visitante.setNombre(nombre);
        visitante.setEmail(email);
        visitante.setPresupuesto(total);
        em.persist(visitante);
        em.getTransaction().commit();
        em.close();

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Gracias por usar el generador de presupuestos</h2>");
        response.getWriter().println("<p>Nombre: " + nombre + "</p>");
        response.getWriter().println("<p>Email: " + email + "</p>");
        response.getWriter().println("<p>Presupuesto estimado: $" + total + "</p>");
        response.getWriter().println("</body></html>");
    }
}
