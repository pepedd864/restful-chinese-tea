package restful.api;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("/test")
public class TestAPI {
    @GET
    @Path("/hello")
    public String hello() {
        return "Hello";
    }
}