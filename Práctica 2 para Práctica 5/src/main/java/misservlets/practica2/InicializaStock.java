package misservlets.practica2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InicializaStock
 *
 */
@WebListener
public class InicializaStock implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InicializaStock() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext context = sce.getServletContext();
         String archStock = context.getInitParameter("stock");
         BufferedReader cat = null;
         Hashtable catalogo = new Hashtable();
         try {
        	cat = new BufferedReader (new InputStreamReader(context.getResourceAsStream(archStock)));
        	String linea;
            while ((linea = cat.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();
                    double precio = Double.parseDouble(partes[1].trim());
                    catalogo.put(nombre, precio);
                }
        	context.setAttribute("stock", catalogo);
         }
        }
         catch(Exception e) {}
         finally {
        	 if (cat!=null) {
        		 try {
        				 cat.close(); 
        			} 
        	 	catch (Exception e) {}
 
        	 }
   
         }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
