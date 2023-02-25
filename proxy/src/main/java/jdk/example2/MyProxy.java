package jdk.example2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MyProxy {

    protected InvocationHandler h;

    private final static AtomicInteger PROXY_CNT = new AtomicInteger(0);

    private MyProxy() {
    }

    protected MyProxy(InvocationHandler h) {
        this.h = h;
    }

    public static Object newProxyInstance(Class<?>[] interfaces, InvocationHandler h)
            throws Exception {
        String proxyClassName = "com/sun/proxy/$Proxy" + PROXY_CNT.getAndIncrement();
        // 创建代理类
        byte[] proxyClassByteCode = MyProxyFactory
                .createProxyClass(proxyClassName, interfaces);
        // 加载代理类
        Class<?> proxyClass = ByteCodeUtils.loadClass(proxyClassName, proxyClassByteCode);
        // 反射创建代理类
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        return constructor.newInstance(h);
    }

}