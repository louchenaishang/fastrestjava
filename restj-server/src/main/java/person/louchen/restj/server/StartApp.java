package person.louchen.restj.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by louchen on 2016/11/30.
 */
public class StartApp{

    private static void initLogBackConfigurations() throws Exception {
        //初始化logback
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        context.reset();
        InputStream xml = StartApp.class.getClassLoader().getResourceAsStream("conf/logback.xml");
        configurator.doConfigure(xml);
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

    public static void main(String[] args) throws Exception{
        initLogBackConfigurations();

        JettyServer server = new JettyServer();
        server.start();
    }

}
