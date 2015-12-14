package suhael.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("form")
public class FormResource {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Person createOrder(@FormParam("field") String field,
                              @FormParam("name") String name) {

        System.out.println("id: " + field + ", name:" + name);
        return new Person();
    }
}