package jdk.example1;


public interface HttpRequestTemplate {
    HttpResponse doGet(HttpRequest httpRequest);
    HttpResponse doPost(HttpRequest httpRequest);
}