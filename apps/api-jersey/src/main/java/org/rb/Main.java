package org.rb;

import java.io.IOException;                    // pour IOException
import java.net.URI;                          // pour URI.create(...)
import org.glassfish.grizzly.http.server.HttpServer;   // Grizzly
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory; // création serveur
import org.glassfish.jersey.server.ResourceConfig;     // Jersey ResourceConfig


public class Main {
    public static void main(String[] args) throws IOException {
        ResourceConfig rc = new ResourceConfig()
            .packages("org.rb.resources"); // package contenant tes @Path
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
            URI.create("http://localhost:8083/"), rc);
        System.out.println("Server started at http://localhost:8083/");
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
    }
}
