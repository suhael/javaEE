package com.suhael.javaee.json;

import com.fasterxml.jackson.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class StreamingApi {

    public void doIt() throws FileNotFoundException {

        try {

            JsonFactory jfactory = new JsonFactory();

            /*** read from file ***/
            JsonParser jParser = jfactory.createJsonParser(new File("C:\\projects\\personal\\github\\javaEE\\target\\classes\\film.json"));

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                //System.out.println(jParser.getText());
                String fieldname = jParser.getCurrentName();

                if ("name".equals(fieldname)) {

                    // current token is "name",
                    // move to next, which is "name"'s value
                    jParser.nextToken();
                    System.out.println(jParser.getText()); // display mkyong

                }

                if ("year".equals(fieldname)) {

                    // current token is "year",
                    // move to next, which is "name"'s value
                    jParser.nextToken();
                    System.out.println(jParser.getIntValue()); // display 29

                }

                if ("actors".equals(fieldname)) {

                    jParser.nextToken(); // current token is "[", move next

                    // messages is array, loop until token equal to "]"
                    while (jParser.nextToken() != JsonToken.END_ARRAY) {

                        System.out.println(jParser.getText());

                    }

                }

            }
            jParser.close();

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {
        try {
            new StreamingApi().doIt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
