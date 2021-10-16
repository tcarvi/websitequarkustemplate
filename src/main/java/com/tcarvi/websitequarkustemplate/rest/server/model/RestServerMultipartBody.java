package com.tcarvi.websitequarkustemplate.rest.server.model;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class RestServerMultipartBody {
    
    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    public String description;

    @RestForm("image")
    public FileUpload file;
}