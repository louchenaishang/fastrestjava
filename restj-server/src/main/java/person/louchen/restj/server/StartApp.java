package person.louchen.restj.server;

/**
 * Created by louchen on 2016/11/30.
 */
public class StartApp {

    public static void main(String[] args) {
        JettyServer server = new JettyServer();
        server.start();
    }

}
