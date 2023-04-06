package com.zy.mybatis.simple_interface_method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Main {

    public static void main(String[] args) throws Exception {
        // 获取类池，这个类池可以以用来生成class
        ClassPool classPool = ClassPool.getDefault();
        // 创建自定义的类,需要传递类名称
        CtClass ctClass = classPool.makeClass("com.zy.mybatis.demo2.Main$Impl");
        // 构建接口
        CtClass ctInterface = classPool.makeInterface("com.zy.mybatis.demo2.UserService");
        // 添加接口到自定义类上(让自己的类实现其接口)
        ctClass.addInterface(ctInterface);
        // 通过CtMethod创建方法，并且绑定到自定义的类上
        CtMethod ctMethod = CtMethod.make("public void name(){System.out.println(123);}", ctClass);
        // 绑定方法到类上
        ctClass.addMethod(ctMethod);
        // 在内存中生成class
        Class aClass = ctClass.toClass();
        // 将创建的对象实例强制类型转换为对应的接口
        UserService userService = (UserService) aClass.newInstance();
        userService.name();
    }
}
