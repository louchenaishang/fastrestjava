package person.louchen.restj.framework.utils.concurrent;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by louchen on 16/8/15.
 */
public class CuratorUtil {
    static class Lock{}
    private CuratorUtil(){}
    private static CuratorUtil instance = null;
    private static final ResourceBundle bundle = ResourceBundle.getBundle("conf/zookeeper");
    private static final Logger logger = LoggerFactory.getLogger(CuratorUtil.class);
    private static final String LOCK_BASE = "/locks/";
    static{
       getInstance();
    }
    public static CuratorUtil getInstance(){
        if(instance!=null){
            return instance;
        }
        synchronized (CuratorUtil.Lock.class){
            if(instance==null){
                synchronized (CuratorUtil.Lock.class){
                    instance = new CuratorUtil();
                    instance.zookeeperConnectionString = bundle.getString("zookeeper.server");
                    instance.client = CuratorFrameworkFactory.newClient(
                            instance.zookeeperConnectionString
                            ,40000
                            ,15000
                            ,new ExponentialBackoffRetry(1000, 3)
                    );//40秒session超时,15秒连接超时,重试机制3次每次1秒
                    instance.client.start();
                    logger.debug(Thread.currentThread().getName()+",zookeeper客户端已启动");
                }
            }
        }
        return instance;
    }

    private String zookeeperConnectionString;
    private CuratorFramework client;

    /**
     * 获取分布式锁
     * @param lockName
     * @return
     */
    public InterProcessMutex getLock(String lockName) {
        if(lockName==null||"".equals(lockName)){
            throw new IllegalArgumentException("lockName can't be empty");
        }
        InterProcessMutex lock = new InterProcessMutex(client, LOCK_BASE+lockName);
        return lock;
    }

    /**
     * 释放锁
     * @param lock
     * @throws Exception
     */
    public void releaseLock(InterProcessMutex lock) throws Exception {
        if(lock!=null){
            if(lock.isAcquiredInThisProcess()){
                lock.release();
            }
        }
    }

    /**
     * 释放锁,不抛异常
     * @param lock
     */
    public void releaseLockQuietly(InterProcessMutex lock){
        try{
            releaseLock(lock);
        }catch (Exception e){
            logger.error("closeQuietly",e);
        }
    }

}
