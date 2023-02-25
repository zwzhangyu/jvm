package jdk.example1;

import java.lang.reflect.Proxy;

public class Main {
    static {
        // jdk1.8之后的配置
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
    }

    public static void main(String[] args) {
        HttpRequestTemplate target = new HttpRequestTemplateImpl();
        // 创建代理对象
        HttpRequestTemplate requestTemplate = (HttpRequestTemplate)
                Proxy.newProxyInstance(Main.class.getClassLoader(),
                        new Class[]{HttpRequestTemplate.class},
                        new HttpRequestInvocationHandler(target));
        requestTemplate.doGet(new HttpRequest());
    }

}
