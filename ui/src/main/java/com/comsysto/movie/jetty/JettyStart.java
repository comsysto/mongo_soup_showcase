package com.comsysto.movie.jetty;

public class JettyStart {

    public static void main(final String[] args) {

        int port;
        String activeSpringProfile;

        // if a parameter is given to this method
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        // if there is a system parameter
        else if (!System.getenv("PORT").isEmpty()) {
            port = Integer.parseInt(System.getenv("PORT"));
        }
        // default
        else {
            port = 8080;
        }

        System.out.println("JettyStart <" + port + ">");



        if (!System.getenv("CRED_FILE").isEmpty()) {
            activeSpringProfile = "cloudControl";

        }  else {
            activeSpringProfile = "local";
        }

        System.setProperty("spring.profiles.active", activeSpringProfile);
        System.out.println("Spring profile <" + activeSpringProfile + ">");

        JettyStarterApplication jettyStarter = new JettyStarterApplication(port);
        jettyStarter.startServer();
    }
}
