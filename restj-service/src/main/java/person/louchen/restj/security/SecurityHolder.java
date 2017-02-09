package person.louchen.restj.security;

/**
 * Created by louchen on 16/9/29.
 */
public class SecurityHolder {

    protected static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String obj){
        threadLocal.set(obj);
    }

    public static String get(){
        return threadLocal.get();
    }

}
