package rest.recursos;

import rest.modelo.Usuario;
import rest.servicio.UsuarioService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final UsuarioService servicio = new UsuarioService();

    @POST
    public Response crear(Usuario u, @Context UriInfo uriInfo) {
        Usuario creado = servicio.crear(u);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(creado.getId())).build();
        return Response.created(uri).entity(creado).build();
    }

    @GET
    public List<Usuario> listar() {
        return servicio.listar();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int id) {
        Usuario u = servicio.buscar(id);
        return u != null ? Response.ok(u).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response modificar(@PathParam("id") int id, Usuario u) {
        boolean ok = servicio.actualizar(id, u);
        return ok ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        boolean ok = servicio.eliminar(id);
        return ok ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
