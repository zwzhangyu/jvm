package com.zy.demo2;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class JavassistTimeCalcExample1 {
    public static void main(String[] args) throws Exception {
        //需要修改的已有的类名和方法名
        String className = "com.zy.demo2.User";
        String methodName = "hello";
        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath("/Users/zhangyu/code/zy/github/jvm/javassist/demo/target/classes");
        CtClass clazz = classPool.get(className);
        CtMethod method = clazz.getDeclaredMethod(methodName);
        method.insertBefore("long start=System.currentTimeMillis();");
        method.insertAfter("System.out.println(\"耗时:\"+(System.currentTimeMillis()-start)+\"ms\");");
        //调用修改的Looper类的loop方法
        User user = (User) clazz.toClass().newInstance();
        user.hello();
    }
}
