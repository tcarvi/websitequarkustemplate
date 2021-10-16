package com.tcarvi.websitequarkustemplate.rest.server.resource;

import com.tcarvi.websitequarkustemplate.rest.server.model.*;
import com.tcarvi.websitequarkustemplate.rest.server.service.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import io.vertx.core.json.JsonObject;
import javax.enterprise.context.SessionScoped;

import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.RestPath;

@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/restServer")
public class RestServerResource {

    @Inject
    RestServerService restServerService;

    @Inject
    RestServerUploadService restServerUploadService;

    @GET
    @Path("/name/{name}")
    public JsonObject getName(@RestPath String name) {
        return restServerService.exec(name);
    }

    @GET
    @Path("/name-async/{name}")
    public JsonObject getNameAsync(@RestPath String name) {
        return restServerService.exec(name);
    }

    @GET
    @Path("/name-uni/{name}")
    public JsonObject getNameMutiny(@RestPath String name) {
        return restServerService.exec(name);
    }

    @GET
    @Path("/{application-name}")
    public JsonObject getApplication(@RestPath String name) {
        return restServerService.exec(name);
    }

    @POST
    @Path("/{form-data}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public JsonObject sendFile(@MultipartForm RestServerMultipartBody formData) {
        try{
            System.out.println(formData);
        } catch(Exception e){
            // do nothing
        }
       
        // MultipartBody body = new MultipartBody();
        // body.fileName = "greeting.txt";
        // body.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));
        return restServerUploadService.receiveMultipartData("formData");
    }

}