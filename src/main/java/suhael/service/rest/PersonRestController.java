package suhael.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("person")
public class PersonRestController {

    @GET
    public String getPerson(){
        return "Person";
    }

}
