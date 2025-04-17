package filtros;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import dao.AccesoDAO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebFilter("/*") 
public class FiltroLogDeAccesos implements Filter {

    private AccesoDAO dao;

    public void init(FilterConfig config) throws ServletException {
        try {
            dao = new AccesoDAO();
        } catch (Exception e) {
            throw new ServletException("Error al inicializar DAO", e);
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String ip = request.getRemoteAddr();
        String recurso = req.getRequestURI();
        String navegador = req.getHeader("User-Agent");
        Timestamp fechaHora = new Timestamp(new Date().getTime());

        try {
            dao.registrarAcceso(fechaHora, ip, recurso, navegador);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    	
    }
} 
