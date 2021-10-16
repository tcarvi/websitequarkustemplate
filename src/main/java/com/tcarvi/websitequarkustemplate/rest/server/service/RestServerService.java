package com.tcarvi.websitequarkustemplate.rest.server.service;

import javax.enterprise.context.ApplicationScoped;
// import org.jboss.logging.Logger;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class RestServerService {

    //private static final Logger LOG = Logger.getLogger(RestServerService.class);

    public JsonObject exec() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("message", "restServer Simple Service Execution");
        return jsonObject;
    }

    public JsonObject exec(String name) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("message", "restServer Simple Service Execution");
        jsonObject.put("parameter", name);
        return jsonObject;    
    }

    /*
    try {
        // your code
    } catch (Exception ex) {
        LOG.info(ex.getMessage()); // for logs or erros
        // your code
    } finally {
        // your codee
    }
    */

}

