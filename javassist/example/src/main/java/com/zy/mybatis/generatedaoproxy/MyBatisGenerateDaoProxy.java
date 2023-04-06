package com.zy.mybatis.generatedaoproxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 测试mybatis的javassist实现通过接口和xml文件动态生成字节码文件并运行数据库操作
 *
 * @author zhangyu
 * @date 2023/4/5
 */
@Slf4j
public class MyBatisGenerateDaoProxy {

    /**
     * 根据数据库连接和接口创建实现类
     */
    public static Object generate(SqlSession session, Class daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();
        // 构造类，动态生成的实现类名称拼接Proxy字符串
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        ctClass.addInterface(ctInterface);
        // 反射获取目标接口的所有方法并去实现子类的逻辑
        Method[] declaredMethods = daoInterface.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            try {
                StringBuffer methodCode = new StringBuffer();
                //添加修饰符
                methodCode.append("public ");
                //添加返回值
                methodCode.append(method.getReturnType().getName() + " ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 添加方法参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    methodCode.append(parameterTypes[i].getName() + " ");
                    methodCode.append("arg").append(i);
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append("){");
                // 括号中间需要写对应的session.insert或session.select方法
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = session.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                // com.zy.mybatis.generatedaoproxy.SqlSessionUtil 工具为自定义的获取数据库连接的方法
                methodCode.append("org.apache.ibatis.session.SqlSession session = com.zy.mybatis.generatedaoproxy.SqlSessionUtil.openSession();");
                // 针对增删改查调用Mybatis的手动处理API
                if (sqlCommandType == SqlCommandType.INSERT) {
                    methodCode.append(" session.insert(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.DELETE) {
                    methodCode.append(" session.delete(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return session.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String resultType = method.getReturnType().getName();
                    methodCode.append("return (" + resultType + ")session.selectOne(\"" + sqlId + "\", arg0);");
                }
                methodCode.append("}");
                log.info("动态生成的方法体内容信息：{}", methodCode);
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
        Object obj = null;
        try {
            Class<?> toClass = ctClass.toClass();
            log.info("动态生成的类：{}", toClass);
            obj = toClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}