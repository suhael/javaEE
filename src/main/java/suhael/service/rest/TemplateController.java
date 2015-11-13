package suhael.service.rest;

import com.suhael.model.TemplateData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("template")
public class TemplateController {

    @GET
    @Path("header")
    @Produces(MediaType.TEXT_HTML)
    public String getHeader(){
        return "<html><head></head><body>hello></body></html>";
    }

    @GET
    @Path("footer")
    @Produces(MediaType.TEXT_HTML)
    public String getFooter(){
        return "<html><head></head><body>hello></body></html>";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("data")
    public Response getData(){
        TemplateData templateData = new TemplateData();
        templateData.setTitle("page title");
        templateData.setFooter("This footer is from the json response ");
//        return templateData;

        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(templateData)
                .build();
    }
}
