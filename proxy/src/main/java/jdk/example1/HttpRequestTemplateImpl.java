package jdk.example1;


public class HttpRequestTemplateImpl implements HttpRequestTemplate {

    @Override
    public HttpResponse doGet(HttpRequest httpRequest) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return new HttpResponse();
    }

    @Override
    public HttpResponse doPost(HttpRequest httpRequest) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return new HttpResponse();
    }

}