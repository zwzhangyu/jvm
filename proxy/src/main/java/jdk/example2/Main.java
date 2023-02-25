package jdk.example2;

import jdk.example1.HttpRequestInvocationHandler;
import jdk.example1.HttpRequestTemplate;
import jdk.example1.HttpRequestTemplateImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpRequestTemplate target = new HttpRequestTemplateImpl();
        HttpRequestTemplate requestTemplate = (HttpRequestTemplate)
                MyProxy.newProxyInstance(
                        new Class[]{HttpRequestTemplate.class},
                        new HttpRequestInvocationHandler(target));
    }
}
