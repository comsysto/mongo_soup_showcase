package com.comsysto.movie.jetty;

public class JettyStart {

    public static void main(final String[] args) {

        int port;
        String activeSpringProfile;
        boolean cloudControlFlag = (System.getenv("CRED_FILE") != null);


        if (cloudControlFlag) {
            activeSpringProfile = "cloudControl";

            if (!System.getenv("PORT").isEmpty()) {
                port = Integer.parseInt(System.getenv("PORT"));
            }
            // default
            else {
                port = 8080;
            }

        } else {
            activeSpringProfile = "local";

            // if a parameter is given to this method
            if (args.length > 0) {
                port = Integer.valueOf(args[0]);
            }
            // default
            else {
                port = 8080;
            }
        }

        System.setProperty("spring.profiles.active", activeSpringProfile);
        System.out.println("active Spring profile <" + activeSpringProfile + ">");


        System.out.println("JettyStart <" + port + ">");

        JettyStarterApplication jettyStarter = new JettyStarterApplication(port);
        jettyStarter.startServer();
    }
}
