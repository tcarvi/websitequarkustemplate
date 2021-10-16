package com.tcarvi.websitequarkustemplate.rest.server.service;

import io.vertx.core.json.JsonObject;
import javax.enterprise.context.ApplicationScoped;
//import org.jboss.logging.Logger;


@ApplicationScoped
public class RestServerUploadService {

    //private static final Logger LOG = Logger.getLogger(RestServerUploadService.class);

    public JsonObject receiveMultipartData(String data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("message", "restServer Simple Service Execution");
        System.out.println(data);
        return jsonObject;
    }

}
