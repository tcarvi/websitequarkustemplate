package com.tcarvi.websitequarkustemplate.rest.server.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.Template;

@Path("hello")
public class HelloResource {

    @Inject
    Template hello;
    // If there is no @Location qualifier provided, 
    //   the field name is used to locate the template. 
    //   In this particular case, we’re injecting a template 
    //   with path templates/hello.txt.

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance get(@QueryParam("name") String name) {
        return hello.data("name", name); 
        // Template.data() returns a new template instance 
        //    that can be customized before the 
        //    actual rendering is triggered. 
        //    In this case, we put the name value under the key name.
        //    The data map is accessible during rendering.
        // Note that we don’t trigger the rendering - 
        //    this is done automatically by a special 
        //    ContainerResponseFilter implementation.
        // Users are encouraged to use Type-safe templates 
        //    that help to organize the templates 
        //    for a specific JAX-RS resource 
        //    and enable type-safe expressions automatically
    }
}