package com.zy.demo2;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistTimeCalcExample2 {
    public static void main(String[] args) throws Exception {
        //需要修改的已有的类名和方法名
        String className = "com.zy.demo2.User";
        String methodName = "hello";
        ClassPool classPool = ClassPool.getDefault();
        classPool.insertClassPath("/Users/zhangyu/code/zy/github/jvm/javassist/demo/target/classes");
        CtClass clazz = classPool.get(className);
        CtMethod method = clazz.getDeclaredMethod(methodName);
        String newName = methodName + "$impl";
        // 修改原有方法的名称
        method.setName(newName);
        //使用原始方法名loop，定义一个新方法，在这个方法内部调用loop$impl
        CtMethod newMethod = CtNewMethod.make("public void "+methodName+"(){" +
                        "long start=System.currentTimeMillis();" +
                        ""+newName+"();" +//调用hello$impl
                        "System.out.println(\"耗时:\"+(System.currentTimeMillis()-start)+\"ms\");" +
                        "}"
                , clazz);
        clazz.addMethod(newMethod);
        //调用修改的Looper类的loop方法
        User user = (User) clazz.toClass().newInstance();
        user.hello();
    }
}
