package person.louchen.restj.server;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.WebApplicationInitializer;
import person.louchen.restj.server.init.MyWebApplicationInitializer;

import java.io.IOException;

/**
 * Created by louchen on 2016/11/30.
 */
public class JettyServer {

    private static final Integer HTTP_PORT = 8082;

    private static final String PATH_CONTEXT = "/";

    public JettyServer() {
    }

    public void start() {
        try {
            Server server = new Server();
            server.setConnectors(this.newConnectors());
            server.setThreadPool(this.newQueuedThreadPool());
            server.setHandler(this.newHandlerCollection());

            server.setStopAtShutdown(true);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置最大和最小处理线程和线程闲置时间
     *
     * @return
     */
    protected QueuedThreadPool newQueuedThreadPool() {
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setMinThreads(20);
        queuedThreadPool.setMaxThreads(300);
        queuedThreadPool.setMaxIdleTimeMs(1000 * 10);
        queuedThreadPool.setName("Jetty-Thread-");
        return queuedThreadPool;
    }

    /**
     * 设置监听端口
     *
     * @return
     */
    protected Connector[] newConnectors() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(HTTP_PORT);
        return new Connector[]{connector};
    }

    protected HandlerCollection newHandlerCollection() throws IOException {
        WebAppContext context = new WebAppContext();
        context.setContextPath(PATH_CONTEXT);
        context.setBaseResource(Resource.newClassPathResource("META-INF/webapp"));

        this.setConfigurations(context);

        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[]{context});

        return handlerCollection;
    }

    private void setConfigurations(WebAppContext webappCtx) {
        webappCtx.setConfigurations(new Configuration[] {
                new AnnotationConfiguration() {
                    @Override
                    public void preConfigure(WebAppContext context) throws Exception {
                        MultiMap map = new MultiMap();
                        map.put(WebApplicationInitializer.class.getName(), MyWebApplicationInitializer.class.getName());
                        context.setAttribute(CLASS_INHERITANCE_MAP, map);
                    }
                }
        });
    }


}
