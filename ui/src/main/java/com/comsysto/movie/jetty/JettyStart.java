package com.comsysto.movie.jetty;

public class JettyStart {

    public static void main(final String[] args) {

        int port;

        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        else if (!System.getenv("PORT").isEmpty()) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        else {
            port = 8080;
        }

        System.out.println("JettyStart <" + port + ">");


        System.setProperty("spring.profiles.active", "default");

        JettyStarterApplication jettyStarter = new JettyStarterApplication(port);
        jettyStarter.startServer();
    }
}
