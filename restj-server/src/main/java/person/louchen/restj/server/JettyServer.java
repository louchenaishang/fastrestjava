package person.louchen.restj.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by louchen on 2016/11/30.
 */
public class JettyServer {

    private static final Integer HTTP_PORT = 8082;

    private static final String PATH_WEBXML = "webapp/WEB-INF/web.xml";

    private static final String PATH_WEBCONTENT = "webapp";

    private static final String PATH_CONTEXT = "/";

    public JettyServer(){}

    public void start(){
        try {
            Server server = new Server();
            server.setConnectors(this.newConnectors());
            server.setHandler(this.newHandler());
            server.setThreadPool(this.newQueuedThreadPool());

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置最大和最小处理线程和线程闲置时间
     * @return
     */
    protected QueuedThreadPool newQueuedThreadPool(){
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setMinThreads(20);
        queuedThreadPool.setMaxThreads(300);
        queuedThreadPool.setMaxIdleTimeMs(1000*10);
        queuedThreadPool.setName("Jetty-Thread-");
        return queuedThreadPool;
    }

    /**
     * 设置监听端口
     * @return
     */
    protected Connector[] newConnectors(){
        SelectChannelConnector connector=new SelectChannelConnector();
        connector.setPort(HTTP_PORT);
        return new Connector[]{connector};
    }

    /**
     * 指定context,指定资源路径,指定web.xml路径
     * @return
     */
    protected WebAppContext newHandler(){
        WebAppContext context = new WebAppContext();
        String path = null;
        if(System.getProperty("webapp.path")==null){
            path = "./";
        }else{
            path = System.getProperty("webapp.path");
        }
        context.setDescriptor(path+PATH_WEBXML);
        context.setResourceBase(path+PATH_WEBCONTENT);
        context.setContextPath(PATH_CONTEXT);
        context.setParentLoaderPriority(true);
        return context;
    }


}
