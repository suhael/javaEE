package suhael.service.rest;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Path("images")
public class ImageController {

    private String imagesFolder = "/resources/";

    @GET
    @Path("{path:.*}")
    @Produces("image/png")
    public Response getUserImage(@PathParam("path") String path) {

        BufferedImage image = stringToImage(imagesFolder + "/" + path);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
//            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        return Response.ok(new ByteArrayInputStream(imageData)).build();

    }

    private BufferedImage stringToImage(String imageString)    {
        //string to ByteArrayInputStream
        BufferedImage bImage = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(imageString.getBytes());
        try {
            bImage = ImageIO.read(bais);
        } catch (IOException ex) {
//            Logger.getLogger(ImageToXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bImage;
    }
}
