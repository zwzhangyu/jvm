package com.zy.mybatis.generatedaoproxy;



import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * 使用javassist动态生成类
 *
 * @author zhangyu
 * @date 2023/4/5
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // 获取类池，这个类池可以以用来生成class
        ClassPool classPool = ClassPool.getDefault();
        // 创建自定义的类,需要传递类名称
        CtClass ctClass = classPool.makeClass("com.zy.mybatis.generatedaoproxy.Main$Impl");
        // 通过CtMethod创建方法，并且绑定到自定义的类上
        CtMethod ctMethod = CtMethod.make("public void insert(String arg0){org.apache.ibatis.session.SqlSession session = com.zy.mybatis.generatedaoproxy.SqlSessionUtil.openSession(); session.insert(\"com.zy.mybatis.generatedaoproxy.EmployeeDao.insert\", arg0);}\n", ctClass);
        // 绑定方法到类上
        ctClass.addMethod(ctMethod);
        // 在内存中生成class
        ctClass.toClass();

        // 经过上面的步骤此时class字节码已经生成到内存中，现可以通过类加载到JVM中
        Class<?> aClass = Class.forName("com.zy.mybatis.generatedaoproxy.Main$Impl");
        Object obj = aClass.newInstance();
        Method declaredMethod = aClass.getDeclaredMethod("insert");
    }
}
