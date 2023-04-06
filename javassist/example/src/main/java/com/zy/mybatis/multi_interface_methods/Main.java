package com.zy.mybatis.multi_interface_methods;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // 获取类池，这个类池可以以用来生成class
        ClassPool classPool = ClassPool.getDefault();
        // 创建自定义的类,需要传递类名称
        CtClass ctClass = classPool.makeClass("com.zy.mybatis.multi_interface_methods.Main$Impl");
        // 构建接口
        CtClass ctInterface = classPool.makeInterface("com.zy.mybatis.multi_interface_methods.UserService");
        // 添加接口到自定义类上(让自己的类实现其接口)
        ctClass.addInterface(ctInterface);
        // 通过CtMethod创建方法，并且绑定到自定义的类上
        Method[] userServiceMethods = UserService.class.getDeclaredMethods();
        Arrays.stream(userServiceMethods).forEach(p -> {
            StringBuilder methodBody = new StringBuilder();
            methodBody.append("public ");
            methodBody.append(p.getReturnType().getName());
            methodBody.append(" ");
            methodBody.append(p.getName());
            methodBody.append("(");
            Class<?>[] parameterTypes = p.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                methodBody.append(parameterType.getName());
                methodBody.append(" ");
                methodBody.append("arg" + i);
                if (i != parameterTypes.length - 1) {
                    methodBody.append(",");
                }
            }
            methodBody.append(")");
            methodBody.append("{System.out.println(1);");
            // 返回值
            String simpleName = p.getReturnType().getSimpleName();
            if ("void".equals(simpleName)) {

            } else if ("int".equals(simpleName)) {
                methodBody.append("return 1; ");
            } else if ("String".equals(simpleName)) {
                methodBody.append("return \"\"; ");
            }


            methodBody.append("}");
            System.out.println(methodBody.toString());

            // 绑定方法到类上
            try {
                CtMethod ctMethod = CtMethod.make(methodBody.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }

        });
        // 在内存中生成class
        Class aClass = ctClass.toClass();
        UserService userService = (UserService) aClass.newInstance();
        userService.find("123");
        userService.insert("123", "456");
        userService.update("123", "456");
        userService.delete();
    }
}
