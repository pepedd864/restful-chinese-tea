tianpackage restful.api;

import jakarta.ws.rs.*;

@Path("/exhibits")
public class ExhibitsAPI {

    @POST
    @Path("/create")
    public String create() {
        return "ExhibitsAPI.create()";
    }

    @DELETE
    @Path("/delete")
    public String delete() {
        return "ExhibitsAPI.delete()";
    }

    @PUT
    @Path("/update")
    public String update() {
        return "ExhibitsAPI.update()";
    }

    @GET
    @Path("/get/{exhibitId}")
    public String get(@PathParam("exhibitId") long exhibitId) {
        return "ExhibitsAPI.get()";
    }

    @GET
    @Path("/get/All")
    public String get() {
        return "ExhibitsAPI.get()";
    }
}
