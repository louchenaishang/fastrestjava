package person.louchen.restj.framework.utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by louchen on 16/8/25.
 */
public class ReflectHelpUtil {

    /**
     * 方法参数名称
     * @param className
     * @param methodName
     * @return
     */
    public static String[] getMethodArgsNames(String className,String methodName) throws NotFoundException {
        String[] paramNames = null;
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get(className);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            paramNames = new String[0];
            return paramNames;
        }
        paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }

    /**
     * 方法参数类型
     * @param className
     * @param methodName
     * @return
     */
    public static Class[] getMethodArgsClasses(String className,String methodName) {
        Class[] paramTypes = null;
        try {
            Class clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String currentMethodName = method.getName();
                if(currentMethodName.equals(methodName)){
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    paramTypes = new Class[parameterTypes.length] ;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        paramTypes[i] = Class.forName(parameterTypes[i].getName());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            //ignore
        }
        return paramTypes;
    }

    /**
     * 反射获取类的方法
     * @param className
     * @param methodName
     * @return
     * @throws ClassNotFoundException
     */
    public static Method getMethod(String className,String methodName) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String currentMethodName = method.getName();
            if(currentMethodName.equals(methodName)){
                return method;
            }
        }
        return null;
    }

}
