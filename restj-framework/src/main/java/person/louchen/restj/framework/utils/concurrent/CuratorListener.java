package person.louchen.restj.framework.utils.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by louchen on 16/8/15.
 */
public class CuratorListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(CuratorListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("CuratorListener contextInitialized");
        CuratorThread ct = new CuratorThread();
        ct.setDaemon(true);
        ct.setName("CuratorThread");
        ct.start();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("CuratorListener contextDestroyed");
    }

    static class CuratorThread extends Thread{
        @Override
        public void run() {
            super.run();
            CuratorUtil.getInstance();
        }
    }

}
