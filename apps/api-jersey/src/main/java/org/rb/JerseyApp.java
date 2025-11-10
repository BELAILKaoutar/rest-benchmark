package org.rb;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

public class JerseyApp extends ResourceConfig {
    public JerseyApp() {
        packages("org.rb.resources"); // package où se trouvent tes @Path
        register(JacksonFeature.class); // JSON via Jackson
    }
}
