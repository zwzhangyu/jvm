package cglib.example1;

import jdk.example1.HttpRequest;
import jdk.example1.HttpRequestTemplateImpl;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class CglibAop {

    static {
        // 代理类class文件存入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/tmp");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HttpRequestTemplateImpl.class);
        enhancer.setCallback(new HttpRequestMethodInterceptor());
        HttpRequestTemplateImpl requestTemplate =
                (HttpRequestTemplateImpl) enhancer.create();
        requestTemplate.doGet(new HttpRequest());
    }
}